/**
 * LT1153. String Transforms Into Another String
 *
 * Given two strings str1 and str2 of the same length, determine whether you can transform 
 * str1 into str2 by doing zero or more conversions. In one conversion you can convert all 
 * occurrences of one character in str1 to any other lowercase English character.
 *
 * Return true if and only if you can transform str1 into str2.
 */

public class StringTransformsIntoAnotherString {
  public boolean canConvert(String str1, String str2) {
    if (str1.length() != str2.length()) return false;
    if (str1.equals(str2)) return true;
        
    Map<Character, Character> map = new HashMap<>();
        
    for (int i = 0; i < str1.length(); i++) {
      char s1 = str1.charAt(i);
      char s2 = str2.charAt(i);
      
      if(map.getOrDefault(s1, s2) != s2)  return false;
      map.put(s1, s2);
    }
    
    return new HashSet<Character>(map.values()).size() < 26;
  }
}

/**
 * Java Map API
 * default V | getOrDefault(Object key, V defaultValue)
 * Returns the value to which the specified key is mapped, 
 * or defaultValue if this map contains no mapping for the key.
 */
