package high.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CopyUtil {	
	
	//�����ļ�
	
	public static void CopyTo(File f1,File f2){
		long start = System.currentTimeMillis();
		System.out.println("���ƿ�ʼ��");
		int count=0;
		byte[] b = new byte[1024];
		try {
			FileInputStream fis = new FileInputStream(f1);
			FileOutputStream fos = new FileOutputStream(f2);
			BufferedInputStream bis = new BufferedInputStream(fis);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
				while((count=bis.read(b))!=-1){
					bos.write(b);
			}
				bos.flush();
				bos.close();
				bis.close();
				fis.close();
				fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("δ�ҵ��ļ���");
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("������ɣ�����ʱ�䣺"+(end-start)/1000+"�룡");
	}
	
	/****************************************************************************/
	
	//�����ļ�����String�ַ���
	
	public static void CopyToUseStr(String str1,String str2){
	long start = System.currentTimeMillis();
	System.out.println("���ƿ�ʼ��");
	String[] strs1=str1.split("/");
	String[] strs2=str2.split("/");
	String string1="";
	String string2="";
	if(str1.indexOf("/")!=-1){
		for(int i =0;i<strs1.length;i++){
			if(i!=(strs1.length-1)){
				string1 += strs1[i]+File.separator;
			}
			else{
				string1 +=strs1[i];
			}
		}
	}
	if(str2.indexOf("/")!=-1){
		for(int i =0;i<strs2.length;i++){
			if(i!=(strs2.length-1)){
				string2 += strs2[i]+File.separator;
			}
			else{
				string2 +=strs2[i];
			}
		}
	}
	File f1 = new File(string1);
	File f2 = new File(string2);
	byte[] b = new byte[1024];
	try {
		FileInputStream fis = new FileInputStream(f1);
		FileOutputStream fos = new FileOutputStream(f2);
		BufferedInputStream bis = new BufferedInputStream(fis);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		int count=0;
		while((count=bis.read(b))!=-1){
			
				bos.write(b);
		}
		bos.flush();
		fis.close();
		fos.close();
		bis.close();
		bos.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		System.out.println("δ�ҵ��ļ���");
	}
	catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	long end = System.currentTimeMillis();
	System.out.println("������ɣ�����ʱ�䣺"+(end-start)/1000+"�룡");
  }
	/****************************************************************************/
	
	//�����ļ���
	
	public static void CopyFiles(File f1,File f2){
		long start = System.currentTimeMillis();
		System.out.println("���ƿ�ʼ��");
		CopyFiles1(f1,f2);
		long end = System.currentTimeMillis();
		System.out.println("������ɣ�����ʱ�䣺"+(end-start)/1000+"�룡");
	}
	public static void CopyTo2(File f1,File f2){
		int count=0;
		byte[] b = new byte[1024];
		try {
			FileInputStream fis = new FileInputStream(f1);
			FileOutputStream fos = new FileOutputStream(f2);
			BufferedInputStream bis = new BufferedInputStream(fis);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
				while((count=bis.read(b))!=-1){
					bos.write(b);
			}
				bos.flush();
				bos.close();
				bis.close();
				fis.close();
				fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * ����ͼƬ
	 * 
	 * 
	 * ***/
	
	public static void Upload(File f1,String f2){
		int count=0;
		byte[] b = new byte[1024];
		try {
			FileInputStream fis = new FileInputStream(f1);
			FileOutputStream fos = new FileOutputStream(f2);
			BufferedInputStream bis = new BufferedInputStream(fis);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
				while((count=bis.read(b))!=-1){
					bos.write(b);
			}
				bos.flush();
				bos.close();
				bis.close();
				fis.close();
				fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static void CopyFiles1(File f1,File f2){
		f2.mkdirs();
		File[] file = f1.listFiles();
		for(int i=0;i<file.length;i++){
			if(file[i].isFile()){
				CopyTo2(file[i],new File(f2+File.separator+file[i].getName()));
			}
			if(file[i].isDirectory()){
				String file1 = f1+File.separator+file[i].getName();
				String file2 = f2+File.separator+file[i].getName();
				CopyFiles2(file1,file2);
			}
		}
	}
	public static void CopyFiles2(String str1,String str2){
		(new File(str2)).mkdirs();
		File[] file = (new File(str1)).listFiles();
		for(int i =0;i<file.length;i++){
			if(file[i].isFile()){
				CopyTo2(file[i],new File((new File(str2)).getAbsolutePath()+File.separator+file[i].getName()));
			}
			if(file[i].isDirectory()){
				String file1 = str1+File.separator+file[i].getName();
				String file2 = str2+File.separator+file[i].getName();
				CopyFiles2(file1,file2);
			}
		}
	}
	}