/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author Steven Coss
 * @version 2024-03-11
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    private int[] dayCounts;
    private int[] monthCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;
    private String logName;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer(String logName)
    { 
        // Create the array object to hold the hourly
        // access counts.

        hourCounts = new int[24];
        dayCounts = new int[28];
        monthCounts = new int[12];
        this.logName = logName;
        // Create the reader to obtain the data.
        reader = new LogfileReader(logName);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }
    public void analyzeDaylyData()
    {
        while(reader.hasNext()){
            LogEntry entry = reader.next();
            int day = entry.getDay();
            dayCounts[day]++;
        }
    }
    public void analyzeMonthlyData()
    {
        while(reader.hasNext()){
            LogEntry entry = reader.next();
            int month = entry.getMonth();
            monthCounts[month]++;
        }
    }
    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) 
        {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    public void printdayCounts()
    {
        System.out.println("day: Count");
        for(int day = 0; day < dayCounts.length; day++) 
        {
            System.out.println(day + ": " + dayCounts[day]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    public int numberOfAccesses()
    {
        int total = 0;
        for (int i =0; i < hourCounts.length ; i++)
        {
            total += hourCounts[i];
        }
        return total;
    }
    public void busiestHour()
    {
        int biggest = 0;
        int hour =0;
        for (int i =0; i < hourCounts.length ; i++)
        {
            if(hourCounts[i]>biggest)
            {
                biggest = hourCounts[i];
                hour = i;
            }
            if(hourCounts[i] == biggest)
            {
                //consider doing something
            }
        }
        System.out.println( "The busiest hour is :"+hour);
    }
    public void quiestestHour()
    {
        int smallest = hourCounts[0];
        int hour =0;
        for (int i =0; i < hourCounts.length ; i++)
        {
            if(hourCounts[i]<smallest)
            {
                smallest = hourCounts[i];
                hour = i;
            }
            if(hourCounts[i] == smallest && i != 0)
            {
                //consider doing something
            }
        }
        System.out.println( "The quietest hour is :"+hour);
    }
    public void busiestTwoHour()
    {
        int biggest =0;
        int firstHour=0;
        int secondHour=0;
        for(int i =0; i < hourCounts.length ; i++)
        {
            if(hourCounts[i]>= biggest)
            {
                biggest = hourCounts[i];
                secondHour = firstHour;
                firstHour = i;
            }
        }
        System.out.println("The busiest Two hours are :"+firstHour+" "+secondHour);
    }
    public void questestDay()
    {
        int smallest = dayCounts[0];
        int day =0;
        for (int i =0; i < dayCounts.length ; i++)
        {
            if(dayCounts[i]<smallest)
            {
                smallest = dayCounts[i];
                day = i;
            }
            if(hourCounts[i] == smallest && i != 0)
            {
                //consider doing something
            }
        }
        System.out.println( "The quietest day is :"+day);
    }
    public void busiestDay()
    {
        int biggest = 0;
        int day =0;
        for (int i =0; i < dayCounts.length ; i++)
        {
            if(dayCounts[i]>biggest)
            {
                biggest = dayCounts[i];
                day = i;
            }
            if(dayCounts[i] == biggest)
            {
                //consider doing something
            }
        }
        System.out.println( "The busiest day is :"+day);
        
    }
    public void totalAccessesPerMonth()
    {
        System.out.println("month: Count");
        for(int month = 0; month < monthCounts.length; month++) 
        {
            System.out.println(month + ": " + monthCounts[month]);
        }
    }
    public void questestMonth()
    {
        int smallest = monthCounts[0];
        int month =0;
        for (int i =0; i < monthCounts.length ; i++)
        {
            if(monthCounts[i]<smallest)
            {
                smallest = monthCounts[i];
                month = i;
            }
            if(monthCounts[i] == smallest && i != 0)
            {
                //consider doing something
            }
        }
        System.out.println( "The quietest month is :"+month);
    }
    public void busiestMonth()
    {
        int biggest = 0;
        int month =0;
        for (int i =0; i < monthCounts.length ; i++)
        {
            if(monthCounts[i]>biggest)
            {
                biggest = dayCounts[i];
                month = i;
            }
            if(monthCounts[i] == biggest)
            {
                //consider doing something
            }
        }
        System.out.println( "The busiest day is :"+month);
    }
    public void averageAccessesPerMonth()
    {
        System.out.println("month: Average");
        for (int i =0 ; i< monthCounts.length; i++)
        {
            //System.out.println( i +" : " + monthCounts[i]/
        }
    }
    
}
