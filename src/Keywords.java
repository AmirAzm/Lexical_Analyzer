

public class Keywords {
	
	String Kword[] = {"int" , "char" , "float" , "double" , "if" , "else" , "for" , "do" , 
			"while" , "main", "return" , "endif"};
	
	public boolean FindKeyword(String s) {
		
		for(int i = 0 ; i < Kword.length ; i++)
		{
			if (s.equals(Kword[i]))
			{
				return true;
			}
		}
		return false;
	}
}
