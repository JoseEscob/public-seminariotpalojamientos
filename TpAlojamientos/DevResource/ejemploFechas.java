//https://www.jeejava.com/conversion-of-joda-date-time-to-sql-timestamp-and-vice-versa/
import java.sql.Date;
import java.sql.Timestamp;

import org.joda.time.LocalDateTime;

public class JodaDateTimeTest {

  /**
   * @param args
   */
  public static void main(String[] args) {
    LocalDateTime localDateTime = new LocalDateTime();
    System.out.println("LocalDateTime : " + localDateTime);
    System.out.println("LocalDateTime to SQL Date : " + jodatToSQLDate(localDateTime));
    Timestamp timestamp = jodaToSQLTimestamp(localDateTime);
    System.out.println("LocalDateTime to SQL Timestamp : " + timestamp);
    System.out.println("SQL Timestamp to LocalDateTime : " + sqlTimestampToJodaLocalDateTime(timestamp));
  }

  public static Date jodatToSQLDate(LocalDateTime localDateTime) {
    return new Date(localDateTime.toDateTime().getMillis());
  }

  public static Timestamp jodaToSQLTimestamp(LocalDateTime localDateTime) {
    return new Timestamp(localDateTime.toDateTime().getMillis());
  }

  public static LocalDateTime sqlTimestampToJodaLocalDateTime(Timestamp timestamp) {
    return LocalDateTime.fromDateFields(timestamp);
  }

}


//------------------------
  private void ejemploFechas() {
    DateTime currentDate = new DateTime(new Date());
    System.out.println("current date: " + currentDate);
    System.out.println("current day: " + currentDate.getDayOfMonth());

    DateTime tomorrow = currentDate.plusDays(1);
    System.out.println("tomorrow: " + tomorrow.getDayOfMonth());

    DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
    System.out.println("date format: " + formatter.parseDateTime("30/01/1990"));
    System.out.println(formatter.print(currentDate));

  }