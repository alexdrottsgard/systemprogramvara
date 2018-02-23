package memory;

import java.util.Comparator;
import java.util.HashMap;
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
		Pointer address = null;

		for (Link linkFree : freeList) {
			if (linkFree.size >= size) {
				address = new Pointer(linkFree.pointer.pointsAt() + size, this);
				// Om freeLinken blir 0, då tar vi bort den linken
				if (linkFree.size == size) {
					freeList.remove(linkFree);
				} else {
					linkFree.size -= size;
					linkFree.pointer = address;
				}
				usedMap.put(address, size);
				// usedList.add(new Link(size, address));
				break;
			}
		}
		if (address == null) {
			System.out.println("Minnet för litet FeelsBadMan  (╯°□°）╯︵ ┻━┻)");
		}
		System.out.println("ADRESS: " + address.pointsAt());
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
		int sizeToRelease = usedMap.get(p);
		int usedTotal = addressToRelease + sizeToRelease;
		
		int freeAddress = -1;
		int index = -1;
		Link link = null;
		for (Link freeLink : freeList) {
			index = freeList.indexOf(freeLink);
			int freeSize = freeLink.size;
			freeAddress = freeLink.pointer.pointsAt();
			int freeTotal = freeSize + freeAddress;
			
			if (freeTotal == addressToRelease) {
				freeLink.size += sizeToRelease;
				freeAddress = freeLink.pointer.pointsAt();
				usedTotal = freeLink.size;
				link = freeLink;
				usedMap.remove(p);
				break;
			} else {
				// skapa ny nod i freeList.
			}
		}
		if (freeList.get(index + 1) == null) {
			System.out.println("inget efter indexet i linklist");
		} else if (freeList.get(index + 1).pointer.pointsAt() == usedTotal) {
			System.out.println("freeList ska merga med index + 1");
			link.size += freeList.get(index+1).size;
		}
//		for (Link freeLink : freeList) {
//			if (freeAddress == usedTotal - 1) {
//				
//			}
//		}

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
