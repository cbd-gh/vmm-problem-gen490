package mainPkg;

public class ObjPair<O1, O2> {

	// first object in pair
	private O1 first;
	// second object in pair
	private O2 second;
	
	public ObjPair(O1 fst, O2 scnd)
	{
		first = fst;
		
		second = scnd;
	}
	
	// get first object in pair
	public O1 getFirst()
	{
		return first;
	}
	
	// get second object in pair
	public O2 getSecond()
	{
		return second;
	}
}
