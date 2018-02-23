package memory;

import java.util.Comparator;
import java.util.LinkedList;

/**
 * This memory model allocates memory cells based on the first-fit method.
 * 
 * @author "Johan Holmberg, Malmö university"
 * @since 1.0
 */
public class FirstFit extends Memory {
	private LinkedList<Link> freeList = new LinkedList<Link>();
	private int freeListSize;
	private LinkedList<Link> usedList = new LinkedList<Link>();

	class Link implements Comparator {
		int size; // storlek
		Pointer pointer; // Pointer, pekar på vilken adress processen har

		Link(int number, Pointer pointer) {
			this.size = number;
			this.pointer = pointer;
		}
		
		public Link() {
			
		}

		@Override
		public int compare(Object o1, Object o2) {
			Link l1 = (Link) o1;
			Link l2 = (Link) o2;
			
			return l1.pointer.pointsAt()-l2.pointer.pointsAt();
		}
	}

	/**
	 * Initializes an instance of a first fit-based memory.
	 * 
	 * @param size
	 *            The number of cells.
	 */
	public FirstFit(int size) {
		super(size);
		this.freeListSize = size;
		freeList.add(new Link(size, new Pointer(0, this)));
	}

	/**
	 * Allocates a number of memory cells.
	 * 
	 * @param size
	 *            the number of cells to allocate.
	 * @return The address of the first cell.
	 */
	@Override
	public Pointer alloc(int size) {
		Link linkFree;
		Pointer address = null;

		// Hur ska detta lösas, ifall det inte får plats i t.ex. första
		// minnesplatsen.
		for (int i = 0; i < freeList.size(); i++) {
			linkFree = freeList.get(i);
			if (linkFree.size >= size) {
				linkFree.size -= size;
				address = linkFree.pointer = new Pointer(linkFree.pointer.pointsAt() + size, this);
				usedList.add(new Link(size, address));
			} else {
				System.out.println("Minnet för litet FeelsBadMan  (╯°□°）╯︵ ┻━┻)");
			}
		}
		System.out.println("ADRESS: " + address.pointsAt());
		freeList.sort(new Link());
		return address;
	}

	/**
	 * Releases a number of data cells
	 * 
	 * @param p
	 *            The pointer to release.
	 */
	@Override
	public void release(Pointer p) {
		int addressToRelease = p.pointsAt();
		int nextPointer;
		int sizeToRelease = 0;
		int address = 0;
		int newFreeAdress = 0;
		int totalMergeSize = 0;
		Pointer newPointer = null;
		int loopaDettaBitch = freeList.size();
		int count = 0;
		// Går igenom usedList
		for (int i = 0; i < usedList.size(); i++) {
			if (usedList.get(i).pointer.pointsAt() == addressToRelease) {
				sizeToRelease = usedList.get(i).size;
				nextPointer = usedList.get(i).pointer.pointsAt() + sizeToRelease + 1;
				
				// Går igenom freeList
				for (int j = 0; j < loopaDettaBitch; j++) {
					int freeListAdress = freeList.get(j).pointer.pointsAt();	// adress som finns för en Link i FreeList
					int freeListSize = freeList.get(j).size;					// Storleken för ledigt minne för ovanstående adress.
					int freeListBehind = freeListAdress + freeListSize;			// ÄR RÄTT!!! För att senare kunna kolla ifall det är en FREE Link bakom (för merge)
					
					// Kollar om linken innan (-1) är ett ledigt utrymme
					if (freeListBehind == addressToRelease - 1) {
						
						totalMergeSize = freeListSize + usedList.get(i).size;
						newPointer = new Pointer(freeListAdress, this);
						newFreeAdress = freeList.get(j).pointer.pointsAt();
						usedList.remove(i);
						freeList.remove(j);
						freeList.add(new Link(totalMergeSize, newPointer));
						System.out.println("Mergat med utrymme som ligger innan, ny total: " + totalMergeSize);
						
						// Kollar om linken efter (+1) är ett ledigt utrymme
					} else if (freeList.get(j).pointer.pointsAt() == newFreeAdress) {
						int mergeSize = totalMergeSize + freeListSize;
						freeList.remove(j);
						usedList.remove(i);
						freeList.add(new Link(sizeToRelease, newPointer));
						System.out.println("Mergat med utrymme som ligger efter, ny total: " + totalMergeSize);
					} else {
						newPointer = new Pointer(addressToRelease, this);
						freeList.add(new Link(sizeToRelease, newPointer));
						usedList.remove(i);
						System.out.println("NYTT FRITT UTRYMME 3: " + sizeToRelease);
					}
				
					if (freeList.get(j).pointer.pointsAt() == newFreeAdress) {
						int mergeSize = totalMergeSize + freeListSize;
						freeList.remove(j);
						usedList.remove(i);
						freeList.add(j, new Link(mergeSize, newPointer));
						System.out.println("NYTT FRITT UTRYMME 2: " + totalMergeSize);
					}
					
				
					freeList.sort(new Link());
				}
				
			}
			if (usedList.get(i).pointer.pointsAt() == addressToRelease) {
				// if (usedList.get(i).pointer.pointsAt() == ) {
				//
				// }
				usedList.remove(i);
			}
			
		}
	}

	/**
	 * Prints a simple model of the memory. Example:
	 * 
	 * | 0 - 110 | Allocated | 111 - 150 | Free | 151 - 999 | Allocated | 1000 -
	 * 1024 | Free
	 */
	@Override
	public void printLayout() {

		// TODO Implement this!
	}

	/**
	 * Compacts the memory space.
	 */
	public void compact() {
		// TODO Implement this!
	}
}
