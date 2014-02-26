package com.collective.celos;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;

public class MinutelyScheduleTest {

    @Test
    public void minutelyScheduleEmpty() {
        Schedule sch = makeMinutelySchedule();
        ScheduledTime t = new ScheduledTime("2013-11-25T20:00Z");
        Set<ScheduledTime> minutes = sch.getScheduledTimes(t, t);
        Assert.assertEquals(0, minutes.size());
    }

    @Test
    public void minutelyScheduleOneMinute() {
        Schedule sch = makeMinutelySchedule();
        ScheduledTime t1 = new ScheduledTime("2013-11-25T20:00Z");
        ScheduledTime t2 = new ScheduledTime("2013-11-25T20:01Z");
        Set<ScheduledTime> minutes = sch.getScheduledTimes(t1, t2);
        Assert.assertEquals(new TreeSet<ScheduledTime>(Arrays.asList(t1)), minutes);
    }
    
    @Test
    public void minutelyScheduleOneMinuteWithSeconds() {
        Schedule sch = makeMinutelySchedule();
        ScheduledTime t1 = new ScheduledTime("2013-11-25T20:01:05Z");
        ScheduledTime t2 = new ScheduledTime("2013-11-25T20:01:00Z");
        Set<ScheduledTime> minutes = sch.getScheduledTimes(t1, t2);
        Assert.assertEquals(0, minutes.size());
    }

    @Test
    public void minutelyScheduleSomeMinutes() {
        Schedule sch = makeMinutelySchedule();
        ScheduledTime t1 = new ScheduledTime("2013-11-25T20:05:12.00182Z");
        ScheduledTime t2 = new ScheduledTime("2013-11-25T20:08:56.2182Z");
        Set<ScheduledTime> minutes = sch.getScheduledTimes(t1, t2);
        List<ScheduledTime> expectedMinutes =
                Arrays.asList(new ScheduledTime("2013-11-25T20:06Z"),
                              new ScheduledTime("2013-11-25T20:07Z"),
                              new ScheduledTime("2013-11-25T20:08Z"));
        Assert.assertEquals(new TreeSet<ScheduledTime>(expectedMinutes), minutes);
    }
    
    @Test
    public void minutelyScheduleSomeMinutesWithStartingFullMinute() {
        Schedule sch = makeMinutelySchedule();
        ScheduledTime t1 = new ScheduledTime("2013-11-25T20:00Z");
        ScheduledTime t2 = new ScheduledTime("2013-11-25T20:03:56.2182Z");
        Set<ScheduledTime> minutes = sch.getScheduledTimes(t1, t2);
        List<ScheduledTime> expectedMinutes =
                Arrays.asList(new ScheduledTime("2013-11-25T20:00Z"),
                              new ScheduledTime("2013-11-25T20:01Z"),
                              new ScheduledTime("2013-11-25T20:02Z"),
                              new ScheduledTime("2013-11-25T20:03Z"));
        Assert.assertEquals(new TreeSet<ScheduledTime>(expectedMinutes), minutes);
    }

    private MinutelySchedule makeMinutelySchedule() {
        return new MinutelySchedule(Util.newObjectNode());
    }
    
}
