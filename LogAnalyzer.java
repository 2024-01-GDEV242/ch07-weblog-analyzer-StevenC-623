/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
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
        this.logName = logName;
        // Create the reader to obtain the data.
        reader = new LogfileReader(logName+".log");
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

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
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
    public String busiestHour()
    {
        
        return "thing";
    }
}
