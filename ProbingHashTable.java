package proj4;

/**
 * abstract Probing table
 * Note that all "matching" is based on the equals method.
 */
public abstract class ProbingHashTable<AnyType>
{
	/**
	 * Insert into the hash table. If the item is
	 * already present, do nothing.
	 * inc failed if insert function fail
	 * inc success if the insert is success
	 * @param x the item to insert.
	 */
	protected void insert( AnyType x )
	{
		// Insert x as active
		int currentPos = findPos( x );
		if(currentPos == ERROR)
			failed++;
		else{
			array[ currentPos ] = new HashEntry<AnyType>( x, true );
			success++;
		}
	}

	/**
	 * Method that performs probing resolution.
	 * @param x the item to search for.
	 * @return the position where the search terminates.
	 */
	protected abstract int findPos( AnyType x );

	/**
	 * tostring fuction
	 */
	public String toString(){
		String hoo ="";
		for(int i = 0 ; i < array.length; i++){
			if(array[i] == null)
				hoo += i + ": \n";
			else
				hoo += i + ": " + array[i].element + "\n";
		}
		return hoo;
	}
	/**
	 * Remove from the hash table.
	 * @param x the item to remove.
	 */
	protected void remove( AnyType x )
	{
		int currentPos = findPos( x );
		array[ currentPos ].isActive = false;
	}

	/**
	 * Find an item in the hash table.
	 * @param x the item to search for.
	 * @return the matching item.
	 */
	protected boolean contains( AnyType x )
	{
		int currentPos = findPos( x );
		return currentPos != ERROR;
	}

	/**
	 * Make the hash table logically empty.
	 */
	protected void makeEmpty( )
	{
		for( int i = 0; i < array.length; i++ )
			array[ i ] = null;
	}
	/**
	 * find and return the cluster avg
	 * @return the avg of cluster
	 */
	protected double getClustAvg(){
		if(numClust == 0)
			return (double)0;
		else
			return (double)success/numClust;
	}
	
	/**
	 * find max cluster and amount of cluster
	 */
	protected void cluster(){
		maxClust = 0;
		numClust = 0;
		int tempMax = 0;
		for(int i = 0; i < array.length; i++){
			if(array[i] != null){
				numClust++;
				while(i < array.length && array[i]!= null){
					i++;
					tempMax++;
				}
			}
			if(tempMax > maxClust)
				maxClust = tempMax;
			tempMax = 0;
		}
	}
	protected int myhash( AnyType x )
	{
		int hashVal = x.hashCode( );
		hashVal %= array.length;
		if( hashVal < 0 )
			hashVal += array.length;
		return hashVal;
	}
	/**
	 * find the lambda
	 * @return the lambda value
	 */
	protected double getLambda(){
		if(success == 0 || tableSize == 0 )
			return 0;
		return (double)success / tableSize;
	}
	protected void print(int interval){
		cluster();
		System.out.printf("%5d %10.2f %10d %10d %10d %10.2f %10d %10d %10.2f %10d\n", 
				interval, getLambda(), success, failed, total, (double)total/interval, maxProb, numClust, getClustAvg(), maxClust);
	}
	protected static class HashEntry<AnyType>
	{
		public AnyType  element;   // the element
		public boolean isActive;  // false if marked deleted
		public HashEntry( AnyType e )
		{
			this( e, true );
		}
		public HashEntry( AnyType e, boolean i )
		{
			element  = e;
			isActive = i;
		}
	}

	protected static final int DEFAULT_TABLE_SIZE = 11;

	protected HashEntry<AnyType> [ ] array; // The array of elements
	protected int tableSize = 0;
	protected int success, failed, total, maxProb, numClust, maxClust = 0;
	protected final int ERROR = -1;
}
