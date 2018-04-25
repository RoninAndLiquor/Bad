package high.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	// ������ת��Ϊ�ַ�������
	
		public static String TransformString(Date date){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String d1 = sdf.format(date);
			return d1;
		}
		
		/****************************************************************************/
		
		//���ַ�������ת��Ϊϵͳ���͵�����
		
		public static Date TransformDate(String str){
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d2 = null;
			try {
				d2 = sdf1.parse(str);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return d2;
		}
		
		/****************************************************************************/
		
		//��ϵͳ��������ת��Ϊ�ַ���������
		
		public static String TransformYearToString(Date date){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String str = sdf.format(date);
			return str;
		}
		public static Date TransformYear(String str){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date=null;
			try {
				date = sdf.parse(str);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return date;
		}
		/**
		 * ���������ַ���ת��ΪSQL.Date
		 * @param str
		 * @return
		 */
		public static java.sql.Date TransformSqlDate(String str){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date=null;
			try {
				date = sdf.parse(str);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new java.sql.Date(date.getTime());		
		}
		/****************************************************************************/
		/**
		 * 
		 * ��ϵͳ��ǰʱ��ת��Ϊsql.Date
		 * @return
		 */
		public static java.sql.Timestamp TransCurrentToSqlDate(){
			System.out.println(System.currentTimeMillis());
			return  new java.sql.Timestamp(System.currentTimeMillis());		
		}
		/**
		 * 
		 * ��������ת��Ϊutil.Date
		 * @param l
		 * @return
		 */
		public static Date TransTimeMillisToString(long l){
			return new Date(l);
		}
		
		/**
		 * 
		 * ���ַ���ʱ��ת��Ϊ������
		 * 
		 * */
		public static long TransStringToLong(String str){
			Date transformDate = TransformDate(str);
			return transformDate.getTime();	
		}
	
}
