package high.pinyin;

import org.apache.commons.lang3.ArrayUtils;

import high.util.PinyinUtil;

public class PinyinTest {

	public static void main(String[] args){
		String str = "的封神传奇yin yu di";
		String hanziToPinyin = PinyinUtil.hanziToPinyin(str);
		System.out.println(hanziToPinyin);
		
		String hanzi2HeadPinyin = PinyinUtil.hanzi2HeadPinyin(str);
		System.out.println(hanzi2HeadPinyin);
	}
	
}
