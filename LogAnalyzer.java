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
     * Create an object to analyze hourly/dayly/monthly web accesses.
     * @param logName name of the log to be analyed
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
        reader.reset();
    }
     /**
     * Analyze the dayly access data from the log file.
     */
    public void analyzeDaylyData()
    {
        while(reader.hasNext()){
            LogEntry entry = reader.next();
            int day = entry.getDay();
            dayCounts[day]++;
        }
        reader.reset();
    }
     /**
     * Analyze the monthly access data from the log file.
     */
    public void analyzeMonthlyData()
    {
        while(reader.hasNext()){
            LogEntry entry = reader.next();
            int month = entry.getMonth();
            monthCounts[month]++;
        }
        reader.reset();
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
    /**
     * Print the dayly counts.
     * These should have been set with a prior
     * call to analyzeDaylyData.
     */
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
    /**
     * figures out the total number of accesses and returns it as an int
     * needs a prior call to analyzeHourlyData.
     * @return the number of accesses
     */
    public int numberOfAccesses()
    {
        int total = 0;
        for (int i =0; i < hourCounts.length ; i++)
        {
            total += hourCounts[i];
        }
        return total;
    }
    /**
     * finds out which Hour had the most accesses
     * needs a prior call to analyzeHourlyData.
     * in the event of an equal count the earlier hour is printed
     */
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
    /**
     * figures out which hour had the least ammount of accesses
     * needs a prior call to analyzeHourlyData.
     * in the event of an tie the earlier hour is printed
     */
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
    /**
     * finds out which group of 2 hours is the busiest ones and prints out that data
     * needs a prior call to analyzeHourlyData.
     * in the event of an equal count the earlier 2 hours will be printed
     */
    public void busiestTwoHour()
    {
        int biggest =0;
        int firstHour=0;
        int secondHour =0;
        int x = 1;
        
        //int secondHour=0;
        for(int i =0; i < hourCounts.length ; i++)
        {
            if( i == 23)
            {
                x = -23;
            }
            if(hourCounts[i]+hourCounts[i+x]> biggest)
            {
                biggest = hourCounts[i]+hourCounts[i+x];
                //secondHour = firstHour;
                firstHour = i;
            }
        }
        secondHour= firstHour +2;
        if(secondHour>23)
        {
            secondHour-=23;
        }
        
        System.out.println("The busiest Two hours are from "+firstHour+" till "+secondHour);
    }
    /**
     * figures out which day had the least ammount of accesses
     * needs a prior call to analyzeDaylyData.
     * in the event of an tie the earlier day is printed
     */
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
    /**
     * figures out which day had the most ammount of accesses
     * needs a prior call to analyzeDaylyData.
     * in the event of an tie the earlier day is printed
     */
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
    /**
     * figures out and prints how many accesses where made per month
     * needs a prior call to analyzeMonthlyData.
     */
    public void totalAccessesPerMonth()
    {
        System.out.println("month: Count");
        for(int month = 0; month < monthCounts.length; month++) 
        {
            System.out.println(month + ": " + monthCounts[month]);
        }
    }
    /**
     * figures out which month had the least ammount of accesses
     * needs a prior call to analyzeMonthlyData.
     * in the event of an tie the earlier month is printed
     */
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
    /**
     * figures out which month had the most ammount of accesses
     * needs a prior call to analyzeMonthlyData.
     * in the event of an tie the earlier month is printed
     */
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
    /**
     * takes the total number of accesses and devides it down to find the avg # of accesses per month
     */
    public void averageAccessesPerMonth()
    {
        System.out.println("avg Accesses per Month : "+numberOfAccesses()/12);
    }
    
}
