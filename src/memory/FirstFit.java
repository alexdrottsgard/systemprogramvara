package memory;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import edu.princeton.cs.algs4.MinPQ;

/**
 * This memory model allocates memory cells based on the first-fit method.
 * 
 * @author "Johan Holmberg, Malmö university"
 * @since 1.0
 */
public class FirstFit extends Memory {
	private LinkedList<Link> freeList = new LinkedList<Link>();
	private LinkedList<Link> usedList = new LinkedList<Link>();
	private HashMap<Pointer, Integer> usedMap = new HashMap<Pointer, Integer>();

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

			return l1.pointer.pointsAt() - l2.pointer.pointsAt();
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
		Pointer nextAddress = null;
		Pointer oldAddress = null;
		for (Link linkFree : freeList) {
			if (linkFree.size >= size) {
				oldAddress = new Pointer(linkFree.pointer.pointsAt(), this);
				nextAddress = new Pointer(linkFree.pointer.pointsAt() + size, this);
				// Om freeLinken blir 0, då tar vi bort den linken
				if (linkFree.size == size) {
					freeList.remove(linkFree);
				} else {
					linkFree.size -= size;
					linkFree.pointer = nextAddress;
				}
				usedMap.put(oldAddress, size);
				break;
			}
		}
		if (nextAddress == null) {
			System.out.println("Minnet för litet FeelsBadMan  (╯°□°）╯︵ ┻━┻)");
		}
		System.out.println("ADDRESS: " + nextAddress.pointsAt());
		return oldAddress;
	}

	/**
	 * Releases a number of data cells
	 * 
	 * @param p
	 *            The pointer to release.
	 */
	@Override
	public void release(Pointer p) {
		int addressToRelease = p.pointsAt(); //Adressen som ska tas bort
		int sizeToRelease = usedMap.get(p); //Returnerar storleken på minnet som ska tas bort
		int total = addressToRelease + sizeToRelease; //Adress + storlek på det som ska tas bort. Size + 1. (Denna är fel(?))

		int freeAddress = -1;				// Ska lagra adressen på fria minnet.
		int index = -1;						// Ska lagra index för aktuell position i for-loopen nedan

		for (Link freeLink : freeList) {
			index = freeList.indexOf(freeLink); 		// Index för freeLink
			int freeSize = freeLink.size;				//storlek på freeLink i aktuell iterationen
			freeAddress = freeLink.pointer.pointsAt();	// adress för freeLink i aktuell iteration
			System.out.println("freeAddress: " + freeAddress);
			int freeTotal = freeSize + freeAddress;		//total storlek för freeLink med adress Ex: adress = 70 & storlek = 30 alltså: tot = 100.
			
			if (freeAddress < addressToRelease) {
				if (freeTotal == addressToRelease) {
					freeLink.size += sizeToRelease;
					total = freeLink.size;
					usedMap.remove(p);
					break;
				}
			} else {
				System.out.println("Skapar ny link som inte har några fria 'grannar'");
				System.out.println("AddressToRelease: " + addressToRelease + " sizeToRelease: " + sizeToRelease);
				Link newLink = new Link(sizeToRelease, new Pointer(addressToRelease, this));
				freeList.add(index, newLink);
				usedMap.remove(p);
				break;
			}
		}

		if (freeList.get(index + 1).pointer.pointsAt() == total) {
			freeList.get(index).size += freeList.get(index + 1).size;
			System.out.println("dubbelMergeSize = " + freeList.get(index).size);
			freeList.remove(index + 1);
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
		LinkedList<Link> list = new LinkedList<Link>();
		Link link = null;
		
		// sätter in linksen från HashMap till LinkedList
		for (Pointer pointer : usedMap.keySet()) {
			link = new Link(usedMap.get(pointer), pointer);
			list.add(link);

		}
		list.sort(link);
		
		for (Link usedLink : list) {
			System.out.println(usedLink.pointer.pointsAt());
		}
		
//		 TODO Implement this!
	}

	/**
	 * Compacts the memory space.
	 */
	public void compact() {
		// TODO Implement this!
	}
}
