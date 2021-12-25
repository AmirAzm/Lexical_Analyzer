public class Token {
	
	Tokentype type;
	String value;
	
	public enum Tokentype
	{
		kw,
		num,
		id,
		st,
		vr,
		error
	}
	

}
