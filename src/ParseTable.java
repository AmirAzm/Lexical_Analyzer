import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;
import javax.lang.model.element.VariableElement;
import org.omg.CORBA.PUBLIC_MEMBER;

public class ParseTable {
	
	static String Rhst[][];
	static String string;
	static int check1 = 0 , check2 = 0;
	public ParseTable() {
		
	}
	static boolean Parser(String str[]){
		try {
			File file = new File("C:/Users/AmirAz/Desktop/Grammar11.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			int i=0;
			String line[] = new String [200];
			String sp[] = null;
			int lines=0;
			while(bufferedReader.ready()) {
				line[i] = bufferedReader.readLine();
				lines++;
				i++;
			}
			String table[][] = new String[lines][15];
			for(int j = 0 ; j<=lines-1;j++) {
				for(int k = 0 ; k<=14;k++) {
					table[j][k]= "0";
				}
			}
			for(int j = 0 ; j<=lines-1;j++) {
				sp = line[j].split("\\s+");
				for(int k=0 ; k< sp.length ;k++) {
					table[j][k] = sp[k];
				}
			}
			for(int j = 0 ; j<=lines-1;j++) {
				for(int k = 0 ; k<=14;k++) {
					System.out.print(table[j][k] + "\t");
				}System.out.println();
			}
			
			ArrayList<String> landaSet = new ArrayList<String>(); 
			for(int j = 0 ; j<=lines-1;j++) 
					if(table[j][2].equals("land")){
						landaSet.add(table[j][0]);
					}
						
			Stack<String> stack = new Stack<>();
			Rhst= GRHST(table , lines);
			System.out.println("Example of RHST: "+Rhst[0][1]);
			
			int count=0 ;
			String str1 = Rhst[0][1];
			while(str1.charAt(count) == ' ')
				count++;
			String str2 = str1.substring(count);
			System.out.println(str2);
			String[] STRstack = str2.split("\\s+");
			for(int k = 0 ; k < STRstack.length ; k++)
					stack.push(STRstack[k]);
			
			
			int k = 0 ;
			Scanner s = new Scanner(str[0]);
			Token token = new Token();
			token = s.read();
			
			Scanner s2 = new Scanner(str[str.length-1]);
			Token token2 = new Token();
			token2 = s2.read();
			
			boolean flag = true;
			boolean flag1 = false;
			System.out.println("str.size: " +str.length);
			check1 = 0 ; check2 = 0 ;
			for(int w = 0 ; w < str.length ; w++)
				if(str[w].equals("(")) check1 ++;
			
			for(int w = 0 ; w < str.length ; w++)
				if(str[w].equals(")")) check2 ++;
			if(check1 == check2 ){
			while(k < str.length)
			{
				flag = true;
				System.out.println("Top of stack: "+stack.peek());
				System.out.println("token.type: "+token.type);
				if(stack.peek().equals("land"))
				{
					stack.pop();
					flag = false;
				}
				else
				if(token.type.equals(Token.Tokentype.id) && flag == true)
					if(Character.isUpperCase(stack.peek().charAt(0)))
					{
						int a = GerenateParsTable(stack.peek(), "id", table, lines, landaSet);
						System.out.println(a);
						if(a == -1)
						{
							string = "Before or itself : "+str[k] + " in " + str2+" in token "+k;
							return false;
						}
						else
						{
							stack.pop();
							str1 = Rhst[a][1];
							while(str1.charAt(count) == ' ')
								count++;
							str2 = str1.substring(count);
							STRstack = str2.split("\\s+");
							for(int j = 0 ; j < STRstack.length ; j++)
								stack.push(STRstack[j]);
							flag = false;
						}
					}
					else
					{
						if(stack.peek().equals("id"))
						{
							stack.pop();
							k++;
							if(k < str.length)
								s = new Scanner(str[k]);
							token = new Token();
							token = s.read();
							System.out.println("k.size: "+k);
							
							flag = false;
						}
						else
						{
							string = "Error cause:Before or itself : "+ str[k]+" in token "+k;
							return false;
						}
					}
				else if(token.type.equals(Token.Tokentype.num) && flag == true)
					if(Character.isUpperCase(stack.peek().charAt(0)))
					{
						int a = GerenateParsTable(stack.peek(), "num", table, lines, landaSet);
						System.out.println(a);
						if(a == -1)
						{
							string ="Before or itself : "+ str[k] + " in " + str2;
							return false;
						}
						else
						{
							stack.pop();
							str1 = Rhst[a][1];
							while(str1.charAt(count) == ' ')
								count++;
							str2 = str1.substring(count);
							STRstack = str2.split("\\s+");
							for(int j = 0 ; j < STRstack.length ; j++)
									stack.push(STRstack[j]);
							
							flag = false;
						}
					}
					else
					{
						if(stack.peek().equals("num"))
						{
							stack.pop();
							k++;
							if(k < str.length)
							s = new Scanner(str[k]);
							token = new Token();
							token = s.read();
							System.out.println("k.size: "+k);
							flag = false;
						}
						else
						{
							string = "Error cause:Before or itself : "+ str[k];
							return false;
						}
					}
				else if(flag == true)
					if(Character.isUpperCase(stack.peek().charAt(0)))
					{
						int a = GerenateParsTable(stack.peek(), str[k], table, lines, landaSet);
						System.out.println(a);
						if(a == -1)
						{
							string = "Before "+str[k] + " in " + str2 +" in token "+k;
							return false;
						}
						else
						{
							stack.pop();
							str1 = Rhst[a][1];
							while(str1.charAt(count) == ' ')
								count++;
							str2 = str1.substring(count);
							STRstack = str2.split("\\s+");
							for(int j = 0 ; j < STRstack.length ; j++)
									stack.push(STRstack[j]);
							
							flag = false;
						}
					}
					else 
					{
						if(stack.peek().equals(str[k]))
						{
							stack.pop();
							k++;
							if(k < str.length)
							s = new Scanner(str[k]);
							token = new Token();
							token = s.read();
							System.out.println("k.size: "+k);
							flag = false;
						}
						else
						{
							string = "Error cause:Before or itself : "+ str[k]+" in token "+k;
							return false;
						}
					}
			}
			for(int w = 0 ; w < str.length ; w++)
				if(str[w].equals("{"))
					stack.push("{");
					
			for(int w = 0 ; w < str.length ; w++)
				if(str[w].equals("}"))
					if(stack.peek().equals("{"))
						stack.pop();
					else
					{
						stack.push("}");
						w = str.length;
					}
		
			if(Character.isUpperCase(stack.peek().charAt(0)))
					stack.clear();
			if(stack.isEmpty())
			{
				fileReader.close();
				return true;
			}
			
			{
				string = "missing";
				return false;
			}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		string = "missing";
		return false;
	}
	
	static String[][] GRHST(String[][] table , int n ) {
		 String RHST[][] = new String[n][2];
		 int j = 1;
		 for (int i = 0 ; i < n ; i++) {
			 RHST[i][0] = Integer.toString(j);
			 j++;
		 }
		 for (int i = 0 ; i<n ; i++) {
			 String Ex=" ";
			 for(int k =14 ; k>=2 ; k--) {
				 if(table[i][k] !="0") {
					 Ex+=table[i][k];
					 Ex +=" ";
				 }
			 }
			 RHST[i][1]=Ex;
		 }
		 return RHST;
	 }
	
	private static ArrayList<String> first(String s , int n , String[][] table , ArrayList<String> landaSet)
	 {
		 ArrayList<String> fi = new ArrayList<String>();
		 int a = 3 ;
		 
		 for(int i = 0 ; i < n ; i ++)
		 {
			 if(s.equals(table[i][0]))
			 {
				 if(!table[i][2].equals("land"))
				 {
					 if( Character.isUpperCase(table[i][2].charAt(0)) &&
							 Character.isUpperCase(table[i][0].charAt(0)))
					 {
						fi.addAll(first(table[i][2], n, table, landaSet));
						
						for(int j = 0 ; j < landaSet.size() ; j++)
						{
							if( table[i][2].equals(landaSet.get(j)))
							{
								while(!table[i][a].equals("0"))
								{
									if(Character.isUpperCase(table[i][a].charAt(0)) &&
											 Character.isUpperCase(table[i][a].charAt(0)))
									{
										fi.addAll(first(table[i][a], n, table, landaSet));
										a++;
									}
									else
									{
										fi.add(table[i][a]);
										a++;
									}
								}		 
							}
					 	}
					 }
					 else
						 fi.add(table[i][2]);
				 }
			 }
		 }
		
		 return fi;
	 }
	
	static ArrayList<String> follow(String s , int n , String[][] table , ArrayList<String> landaSet)
	 {
		 
		 ArrayList<String> fo = new ArrayList<String>();
		 for(int i = 0 ; i < n ; i++)
		 {
			 for(int j = 2 ; j < 14 ; j++)
			 {
				 int a = j+1;
				 if( s.equals(table[i][j]))
				 {
					 if(table[i][a].equals("0"))
					 {
						 if(! table[i][0].equals(s))
							 fo.addAll(follow(table[i][0], n, table, landaSet));
					 }
					 else if(Character.isUpperCase(table[i][a].charAt(0))) 
					 {
						fo.addAll(first(table[i][a], n, table, landaSet));
						fo.addAll(help(table, a, i, n, landaSet));
						
						}	
					else
					{
						fo.add(table[i][a]);
						a++;
					}
				 }
				 }
			 }
		 
		 
		
		 return fo;
	 }

	 static ArrayList<String> help(String [][] table ,int a,int i,int n ,ArrayList<String> landaSet)
	 {
		 ArrayList<String> fo = new ArrayList<>();
		 for(int k = 0 ; k < landaSet.size() ; k++)
		 {
			 if(table[i][a].equals(landaSet.get(k)))
			 {
				 a++;
				 if(table[i][a].equals("0"))
				 {
					 fo.addAll(follow(table[i][0], n, table, landaSet));
					 return fo;
				 }
				 else
				 {
					 if(Character.isUpperCase(table[i][a].charAt(0))) 
					 {
						fo.addAll(first(table[i][a], n, table, landaSet));
						fo.addAll(help(table, a, i, n, landaSet));
						
						}	
					else
					{
						fo.add(table[i][a]);
						a++;
					} 
				 }
			 }
		 }
		 return fo;
	 }
	
	 static ArrayList<String> predict(int proNum ,int n , String[][] table , ArrayList<String> landaSet)
	 {
		 Set<String> fi = new HashSet<>();
		 fi.addAll(first(table[proNum][0], n, table, landaSet));
		 
		 Set<String> fo = new HashSet<>();
		 
		 int a = 2;
		 Set<String> pred = new HashSet<>();
	
		 if(Character.isUpperCase(table[proNum][a].charAt(0)) &&
				 Character.isUpperCase(table[proNum][a].charAt(0)))
		 {
			 pred.addAll(first(table[proNum][a], n, table, landaSet));
				
			 for(int k = 0 ; k < landaSet.size() ; k++)
				{
					if(table[proNum][a].equals(landaSet.get(k)))
					{
						if( !table[proNum][a+1].equals("0")){
						a++;
						pred.addAll(first(table[proNum][a], n, table, landaSet));
						}
						else
						{
							pred.addAll(follow(table[proNum][0], n, table, landaSet));
						}
					}
					
				} 
		 }
		 else
		 {
			 if(table[proNum][a].equals("land"))
				 pred.addAll(follow(table[proNum][0], n, table, landaSet));
			 else
				 pred.add(table[proNum][a]);
		 }
		 pred.remove("0");		 
		 ArrayList<String> predict = new ArrayList<>(pred);
		 
		 return predict;
		 
	 }
	
	 static int GerenateParsTable (String str1, String str2,String[][] table, int n, ArrayList<String> landaSet)
	 {
		 Set<String> variable = new HashSet<String>();
		 Set<String> terminal = new HashSet<String>();
		 
		 for(int i = 0 ; i < n ; i++)
		 {
			 for(int j = 0 ; j < 14 ; j++)
			 {
				 char ch = table[i][j].charAt(0);
				 if(Character.isUpperCase(ch))
					 variable.add(table[i][j]);
				 else
					 terminal.add(table[i][j]);
				 }
		 }
		 terminal.remove("0");
		 terminal.remove("table[0][1]");
		
 
		 ArrayList<String> listT = new ArrayList<>(terminal);
		 ArrayList<String> listV = new ArrayList<>(variable);
		 ArrayList<Integer> lines = new ArrayList<>();
		 ArrayList<String> pred = new ArrayList<>();
		 
		 HashMap<String, Integer> hash = new HashMap<>();
		 
		 String s = null; 
		 for(int k = 0 ; k < listV.size() ; k++){
			 lines = new ArrayList<>();
			 lines.addAll(findline(listV.get(k), table, n));

			 for(int i = 0 ; i < lines.size() ; i++){
				 
				 pred = new ArrayList<>();
				 pred.addAll(predict(lines.get(i), n, table, landaSet));
				 for(int h = 0 ; h < pred.size() ; h++)
				 {
					 s = listV.get(k) + pred.get(h);
					 hash.put(s, lines.get(i));
				 }
			 }
		 }
		 String result = str1 + str2 ;
		 if(hash.containsKey(result))
			 return hash.get(result);
		 else return -1;
	 }
	
	 static ArrayList<Integer> findline(String s , String[][] table , int n)
	 {
		 ArrayList<Integer> a = new ArrayList<>();
		 for(int i = 0 ; i < n ; i++)
		 {
			 if(table[i][0].equals(s))
			 {
				 a.add(i);
			 }
		 }
		 
		 return a;
	 }
	

}
