package ve.org.bcv.fts.util;

import java.util.*;
import java.util.regex.*;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 Predefined character classes
. 	Any character (may or may not match line terminators)
\d 	A digit: [0-9]
\D 	A non-digit: [^0-9]
\s 	A whitespace character: [ \t\n\x0B\f\r]
\S 	A non-whitespace character: [^\s]
\w 	A word character: [a-zA-Z_0-9]
\W 	A non-word character: [^\w]
 
POSIX character classes (US-ASCII only)
\p{Lower} 	A lower-case alphabetic character: [a-z]
\p{Upper} 	An upper-case alphabetic character:[A-Z]
\p{ASCII} 	All ASCII:[\x00-\x7F]
\p{Alpha} 	An alphabetic character:[\p{Lower}\p{Upper}]
\p{Digit} 	A decimal digit: [0-9]
\p{Alnum} 	An alphanumeric character:[\p{Alpha}\p{Digit}]
\p{Punct} 	Punctuation: One of !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~
\p{Graph} 	A visible character: [\p{Alnum}\p{Punct}]
\p{Print} 	A printable character: [\p{Graph}\x20]
\p{Blank} 	A space or a tab: [ \t]
\p{Cntrl} 	A control character: [\x00-\x1F\x7F]
\p{XDigit} 	A hexadecimal digit: [0-9a-fA-F]
\p{Space} 	A whitespace character: [ \t\n\x0B\f\r]
 */

/**
 *
 * @author Bruno Hernandez
 */
public class RegexValidator {

    /**
     * @param args the command line arguments
     */
	public static final String REGEX_DATE = "[0-9]{8}";
	public static final String REGEX_NUMERIC = "[0-9]+";
	public static final String REGEX_ALPHA = "[a-zA-Z]+";
	public static final String REGEX_ALNUM = "[a-zA-Z0-9]+";
	public static final String REGEX_BLANK = "[ \t]+";
	public static final String REGEX_STRING_WITH_SPECIAL_CHARS = "[\\p{Alnum}\\p{Punct}\\p{Blank}]+";
	public static final String REGEX_STRING = "[\\p{Alnum}\\p{Blank}]+";
	public static boolean isDate(String d){
		boolean v=false;
		if(pregMatch("^"+REGEX_DATE+"$",d)!=null){
			v=true;
		}
		return v;
	}
	public static boolean isString(String d){
		boolean v=false;
		if(pregMatch("^"+REGEX_STRING+"$",d)!=null){
			v=true;
		}
		return v;
	}
	public static boolean isStringWithSpecialChars(String d){
		boolean v=false;
		if(pregMatch("^"+REGEX_STRING_WITH_SPECIAL_CHARS+"$",d)!=null){
			v=true;
		}
		return v;
	}
	public static boolean isBlank(String d){
		boolean v=false;
		if(pregMatch("^"+REGEX_BLANK+"$",d)!=null){
			v=true;
		}
		return v;
	}
	public static boolean isNumeric(String d){
		boolean v=false;
		if(pregMatch("^"+REGEX_NUMERIC+"$",d)!=null){
			v=true;
		}
		return v;
	}
	public static boolean isAlpha(String d){
		boolean v=false;
		if(pregMatch("^"+REGEX_ALPHA+"$",d)!=null){
			v=true;
		}
		return v;
	}
	public static boolean isAlphanumeric(String d){
		boolean v=false;
		if(pregMatch("^"+REGEX_ALNUM+"$",d)!=null){
			v=true;
		}
		return v;
	}
	public static String[] pregMatch(String pattern, String content) {
		String[] strmatch=null;
		Pattern p;
		Matcher m;
		int grupos=0,i=0;
		try{
			// Compile the patten.
			p = Pattern.compile(pattern);

			// Match it.
			m = p.matcher(content);
			//result = m.toMatchResult();
			//System.out.println(m.fi
			// Get all matches.
			grupos=m.groupCount();
			if (m.find() == true){
				strmatch=new String[grupos+1];
				for(i=0;i<=grupos;i++){
					strmatch[i]=m.group(i);
					//System.out.println(i+": "+m.group(i));
				}
			}
		}catch(Exception e){
			strmatch=null;
		}
		return strmatch;
	}	 
	public static ArrayList[] pregMatchAll(String pattern, String content) {
		String[][] strmatch=null;
		Pattern p;
		Matcher m;
		ArrayList[] matchs=null;
		int grupos=0,i=0;
		try{
			// Compile the patten.
			p = Pattern.compile(pattern);

			// Match it.
			m = p.matcher(content);
			//result = m.toMatchResult();
			//System.out.println(m.fi
			// Get all matches.
			grupos=m.groupCount();
			//System.out.println(grupos+"REGEXP");
			while (m.find() == true){
				//System.out.println(grupos+"WHILE");
				if(matchs==null){
					//System.out.println(grupos+"if");
					matchs=new ArrayList[grupos+1];
					//System.out.println(grupos+"ifend");
					for(i=0;i<=grupos;i++){
						//System.out.println(grupos+"for0");
						matchs[i]=new ArrayList();
						//System.out.println(grupos+"for1");
					}
				}
				//System.out.println(i+" xx: "+m.group(i));
				for(i=0;i<=grupos;i++){
					matchs[i].add(m.group(i));
					//System.out.println(i+": "+m.group(i));
				}
			}
			/*if(matchs.length>0){
				strmatch=new String[matchs.length][matchs[0].size()];
				for(i=0;i<=matchs.length;i++){
					matchs[i].add(m.group(i));
					System.out.println(i+": "+m.group(i));
				}
			}*/
		}catch(Exception e){
			e.printStackTrace();
		}
		return matchs;
	}	 
}
