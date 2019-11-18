/** 
 * s: all elements to the left side of slow (excluding s) are results that are processed
 * f: the current index being processed 
 *   (all elements to the right side of the fast pointer have not been processed)
 * return array[s-1]
 * 
 * initialize s = f = next_f = 0
 * 1) next_f = f keep doing next_f++ util a[next_f] != a[f]
 * 2) 
 *    case 1: a[next_f] - a[f] == 1, s++, f++ or f = next_f  (element appear only once, so keep one)
 *    case 2: a[next_f] - a[f]   > 1, f = next_f             (element appear more than one, keep none)
 */

public class ArrayDeduplicationIII {

}
