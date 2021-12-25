import java.io.*;

public class Scanner extends Token{
	char ch =' ';
	String str ;
	String result ;
	Token token;
	public Scanner(String source) {
		token = new Token();
		str = source;
		result="";
	}
	static boolean isNum(char c)
	{
		return c>= '0' && c<= '9';
	}
	static boolean isalpha(char c)
	{
		return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z'; 
	}
	public Token read()
	{				
					ch = str.charAt(0);
					switch(ch)
					{
					case ' ': case '\t': case '\n': case '\r': break;
					case '(':
						token.type = Tokentype.st ;
						token.value= "(" ;
						return token;
					case ')':
						token.type = Tokentype.st ;
						token.value= ")" ;
						return token;
					case '+':
						if(str.length() == 2)
						{
							ch = str.charAt(1);
						
							if( ch == '+')
							{
								token.type = Tokentype.st ;
								token.value = "++";
								return token;
							}
							else
							{
								token.type = Tokentype.st;
								token.value = "+";
								return token;
							
							}
						}
						else if(str.length() > 2)
						{
							token.type = Tokentype.error;
							return token;
						}
						else
						{
							token.type = Tokentype.st;
							token.value = "+";
							return token;
						
						}
					case '-':
						if(str.length() == 2)
						{
							ch = str.charAt(1);
							if( ch == '-')
							{
								token.type = Tokentype.st ;
								token.value = "--";
								return token;
							}
							else
							{
								token.type = Tokentype.st;
								token.value = "-";
								return token;
							}
						}
						else if(str.length() > 2)
						{
							token.type = Tokentype.error;
							return token;
						}
						else
						{
							token.type = Tokentype.st;
							token.value = "-";
							return token;
						}
					case ';':
						token.type = Tokentype.st;
						token.value = ";";
						return token;
					case '*':
						token.type = Tokentype.st;
						token.value = "*";
						return token;
					case '/':
						token.type = Tokentype.st;
						token.value = "/";
						return token;
					case '}':
						token.type = Tokentype.st;
						token.value = "}";
						return token;
					case '{':
						token.type = Tokentype.st;
						token.value = "{";
						return token;
					case ',':
						token.type = Tokentype.st;
						token.value = ",";
						return token;
					case '%':
						token.type = Tokentype.st;
						token.value = "%";
						return token;
					case '!':
						if(str.length() == 2 && str.charAt(1) == '=')
						{
							token.type = Tokentype.st;
							token.value = "!=";
							return token;
						}
						else
						{
							token.type = Tokentype.error;
							return token;
						}
					case '&':
						if(str.length() == 2 && str.charAt(1) == '&')
						{
							token.type = Tokentype.st;
							token.value = "&&";
							return token;
						}
						else
						{
							token.type = Tokentype.error;
							return token;
						}
					case '|':
						if(str.length() == 2 && str.charAt(1) == '|')
						{
							token.type = Tokentype.st;
							token.value = "||";
							return token;
						}
						else
						{
							token.type = Tokentype.error;
							return token;
						}
								
					case '=':
						if(str.length() == 2)
						{
							ch = str.charAt(1);
							if( ch == '=')
							{
								token.type = Tokentype.st ;
								token.value = "==";
								return token;
							}
							else if(str.length() > 2)
							{
								token.type = Tokentype.error;
								return token;
							}
							else
							{
								token.type = Tokentype.st;
								token.value = "=";
								return token;
							}
						}
						else
						{
							token.type = Tokentype.st;
							token.value = "=";
							return token;
						}
					case '>':
						if(str.length() == 2)
						{
							ch = str.charAt(1);
							if( ch == '=')
							{
								token.type = Tokentype.st ;
								token.value = ">=";
								return token;
							}
							else
							{
								token.type = Tokentype.error;
								return token;
							}
						}
						else if(str.length() > 2)
						{
							token.type = Tokentype.error;
							return token;
						}
						else
						{
							token.type = Token.Tokentype.st;
							token.value = ">";
							return token;
						}
					case '<':
						if(str.length() == 2)
						{
							ch = str.charAt(1);
							if( ch == '=')
							{
								token.type = Tokentype.st ;
								token.value = "<=";
								return token;
							}
							else
							{
								token.type = Tokentype.error;
								return token;
							}
						}
						else if(str.length() > 2)
						{
							token.type = Tokentype.error;
							return token;
						}
						else
						{
							token.type = Tokentype.st;
							token.value = "<";
							return token;
						}
					default :
						int j = 0;
						if(isalpha(ch))
						{
							
							result += ch;
							for(int i = 1 ; i < str.length() ; i++)
							{
								ch = str.charAt(i);
								if(isalpha(ch) || isNum(ch) || ch=='_'){
									result += ch;
								}
								else
								{
									i = str.length();
								}
	                    		
	                    	}
							Keywords key = new Keywords();
							if(key.FindKeyword(result))
							{
								token.type = Tokentype.kw;
								token.value = result;
								return token;
							}
							else
							{
								token.type = Tokentype.id;
								token.value = result;
								return token;
							}
	                 	
						}
	                    if(isNum(ch))
	                    {
	                    	
								result=" ";
								result += ch;
								for(int i = 1 ; i < str.length() ; i++)
								{
									
									ch = str.charAt(i);
									if(isNum(ch) || ch == '.'){
										result += ch;
									}
									else
									{
										i = str.length();
									}
		                    		
		                    	}
	                    	
	                    	token.type = Tokentype.num;
	                    	token.value = result;
	                    	return token;
	                    }
	                    else
	                    {
	                    	token.type = Tokentype.error;
	                    	return token;
						}
					}
					token.type = Tokentype.num;
					token.value = "null";
					return token;
				
		}
}

