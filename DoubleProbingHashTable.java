package proj4;

import java.util.ArrayList;

// DoubleProbing Hash table class
//
// CONSTRUCTION: an approximate initial size or default of 101
//
// ******************PUBLIC OPERATIONS*********************
// bool insert( x )       --> Insert x
// bool remove( x )       --> Remove x
// bool contains( x )     --> Return true if x is present
// void makeEmpty( )      --> Remove all items


/**
 * Probing table implementation of hash tables.
 * Note that all "matching" is based on the equals method.
 * @author Mark Allen Weiss
 */
public class DoubleProbingHashTable<AnyType> extends ProbingHashTable
{


	/**
	 * Construct the hash table.
	 */
	public DoubleProbingHashTable( )
	{
		this(DEFAULT_TABLE_SIZE, 7);
	}

	/**
	 * Construct the hash table.
	 * @param prime the approximate initial size.
	 */
	public DoubleProbingHashTable(int tableSize, int R) {
		this.tableSize = tableSize;
		this.R = R;
		array = new HashEntry[ tableSize];
		makeEmpty( );
	}



	/**
	 * Method that performs double probing resolution.
	 * @param x the item to search for.
	 * @return the position where the search terminates.
	 */
	protected int findPos(Object x) {
		ArrayList<Integer> visited = new ArrayList<Integer>();
		total++;
		maxProb = 1;
		int currentPos = myhash( x );
		int offset = R - x.hashCode() % R;
		while(!visited.contains(currentPos) && array[ currentPos ] != null && !array[ currentPos ].element.equals( x ) )
		{
			total++;
			maxProb++;
			visited.add(currentPos);
			currentPos = (currentPos + offset) % tableSize;
		}
		if(visited.contains(currentPos))
			return ERROR;
		return currentPos;
	}
	private int R = 0;
	// Simple main
	/*    public static void main( String [ ] args )
    {
        QuadraticProbingHashTable<String> H = new QuadraticProbingHashTable<String>( );

        final int NUMS = 400000;
        final int GAP  =   37;

        System.out.println( "Checking... (no more output means success)" );


        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
            H.insert( ""+i );
        for( int i = 1; i < NUMS; i+= 2 )
            H.remove( ""+i );

        for( int i = 2; i < NUMS; i+=2 )
            if( !H.contains( ""+i ) )
                System.out.println( "Find fails " + i );

        for( int i = 1; i < NUMS; i+=2 )
        {
            if( H.contains( ""+i ) )
                System.out.println( "OOPS!!! " +  i  );
        }
    }
	 */
}
