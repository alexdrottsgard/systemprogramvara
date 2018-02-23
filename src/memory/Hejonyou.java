//import memory.FirstFit.Link;
//
////package memory;
////
////import memory.FirstFit.Link;
////
////public class Hejonyou {
////
////	for (int i = 0; i < usedList.size(); i++) {
////		if (usedList.get(i).pointer.pointsAt() == addressToRelease) {
////			sizeToRelease = usedList.get(i).size;
////			nextPointer = usedList.get(i).pointer.pointsAt() + sizeToRelease + 1;
////			
////			// Går igenom freeList
////			for (int j = 0; j < loopaDettaBitch; j++) {
////				int freeListAdress = freeList.get(j).pointer.pointsAt();	// adress som finns för en Link i FreeList
////				int freeListSize = freeList.get(j).size;					// Storleken för ledigt minne för ovanstående adress.
////				int freeListBehind = freeListAdress + freeListSize;			// ÄR RÄTT!!! För att senare kunna kolla ifall det är en FREE Link bakom (för merge)
////				
////				// Kollar om linken innan (-1) är ett ledigt utrymme
////				if (freeListBehind == addressToRelease - 1) {
////					
////					totalMergeSize = freeListSize + usedList.get(i).size;
////					newPointer = new Pointer(freeListAdress, this);
////					newFreeAdress = freeList.get(j).pointer.pointsAt();
////					usedList.remove(i);
////					freeList.remove(j);
////					freeList.add(new Link(totalMergeSize, newPointer));
////					System.out.println("NYTT FRITT UTRYMME 1: " + totalMergeSize);
////					
////					// Kollar om linken efter (+1) är ett ledigt utrymme
////				} else if (freeList.get(j).pointer.pointsAt() == newFreeAdress) {
////					int mergeSize = totalMergeSize + freeListSize;
////					freeList.remove(j);
////					usedList.remove(i);
////					freeList.add(new Link(sizeToRelease, newPointer));
////					System.out.println("NYTT FRITT UTRYMME 2: " + totalMergeSize);
////				} else {
////					newPointer = new Pointer(addressToRelease, this);
////					freeList.add(new Link(sizeToRelease, newPointer));
////					usedList.remove(i);
////					System.out.println("NYTT FRITT UTRYMME 3: " + sizeToRelease);
////				}
////			
////				if (freeList.get(j).pointer.pointsAt() == newFreeAdress) {
////					int mergeSize = totalMergeSize + freeListSize;
////					freeList.remove(j);
////					usedList.remove(i);
////					freeList.add(j, new Link(mergeSize, newPointer));
////					System.out.println("NYTT FRITT UTRYMME 2: " + totalMergeSize);
////				}
////				
////			
////				freeList.sort(new Link());
////			}
////			
////		}
////		if (usedList.get(i).pointer.pointsAt() == addressToRelease) {
////			// if (usedList.get(i).pointer.pointsAt() == ) {
////			//
////			// }
////			usedList.remove(i);
////		}
////		
////	}
////}
//
//
///**
// * 
// * DETTA ÄR NEDANFÖR ÄR GAMMALT
// * 
// *    
// * 
// * 
// * 	for (int i = 0; i < usedList.size(); i++) {
//			if (usedList.get(i).pointer.pointsAt() == addressToRelease) {
//				sizeToRelase = usedList.get(i).size;
//				nextPointer = usedList.get(i).pointer.pointsAt() + sizeToRelase + 1;
//				
//				// Går igenom freeList
//				for (int j = 0; j < freeList.size(); j++) {
//					int freeListAdress = freeList.get(j).pointer.pointsAt();	// adress som finns för en Link i FreeList
//					int freeListSize = freeList.get(j).size;					// Storleken för ledigt minne för ovanstående adress.
//					int freeListBehind = freeListAdress + freeListSize;			// ÄR RÄTT!!! För att senare kunna kolla ifall det är en FREE Link bakom (för merge)
//					
//					// Kollar om linken innan (-1) är ett ledigt utrymme
//					if (freeListBehind == addressToRelease - 1) {
//						
//						totalMergeSize = freeListSize + usedList.get(i).size;
//						newPointer = new Pointer(freeListAdress, this);
//						newFreeAdress = freeList.get(j).pointer.pointsAt();
//						usedList.remove(i);
//						freeList.remove(j);
//						freeList.add(new Link(totalMergeSize, newPointer));
//						System.out.println("NYTT FRITT UTRYMME 1: " + totalMergeSize);
//					}
//					// Kollar om linken efter (+1) är ett ledigt utrymme
//					if (freeList.get(j).pointer.pointsAt() == newFreeAdress) {
//						int mergeSize = totalMergeSize + freeListSize;
//						freeList.remove(j);
//						usedList.remove(i);
//						freeList.add(j, new Link(mergeSize, newPointer));
//						System.out.println("NYTT FRITT UTRYMME 2: " + totalMergeSize);
//					}
//					
//				
//					freeList.sort(new Link());
//				}
//				if (usedList.get(i + 1) != null) {
//					if (freeList.peek().size > 0) {
//
//					}
//				}
//			}
//			if (usedList.get(i).pointer.pointsAt() == addressToRelease) {
//				// if (usedList.get(i).pointer.pointsAt() == ) {
//				//
//				// }
//				usedList.remove(i);
//			}
//			
//		}
// * 
// * 
// */
