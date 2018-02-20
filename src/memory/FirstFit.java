package memory;

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

	class Link {
		int size; // storlek
		Pointer pointer; // Pointer, pekar på vilken adress processen har

		Link(int number, Pointer pointer) {
			this.size = number;
			this.pointer = pointer;
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
		int size = 0;
		int address = 0;

		for (int i = 0; i < usedList.size(); i++) {
			if (usedList.get(i).pointer.pointsAt() == addressToRelease) {
				size = usedList.get(i).size;
				nextPointer = usedList.get(i).pointer.pointsAt() + size + 1;
				for (int j = 0; j < freeList.size(); j++) {
					if (freeList.get(i).pointer.pointsAt() == nextPointer) {
						
					}
				}
				if (usedList.get(i + 1) != null) {
					if (freeList.peek().size > 0) {

					}
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
