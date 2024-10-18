/**
 * 681. Seven Puzzle
 * Given eight cards with number 0, 1, 2, ..7 on them, the cards are placed in two rows with 4 cards in each row.
 * In each step only card 0 could swap with one of its adjacent(top, right, bottom, left) card. Your goal is to make all cards placed in order like this:
 *
 * 0 1 2 3
 * 4 5 6 7
 *
 * Find the minimum number of steps from the given state to the final state. If there is no way to the final state,
 * then return -1.
 *
 * The state of cards is represented by an array of integer, for example [0,1,2,3,4,5,6,7]
 * where the first four numbers are in the first row from left to right while the others
 * are placed in the second row from left to right.
 *
 * Example:
 * Input: [4,1,2,3,5,0,6,7]       Output: 2
 *
 * Initial state is:
 *
 * 4 1 2 3
 * 5 0 6 7
 *
 * First swap 0 with 5, then the state is:
 *
 * 4 1 2 3
 * 0 5 6 7
 *
 * Then swap 0 with 4, then we get the final state:
 *
 * 0 1 2 3
 * 4 5 6 7
 */
