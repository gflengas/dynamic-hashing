public class DeletedEntry extends HashEntry {
      private static DeletedEntry entry = null;
 
      private DeletedEntry() {
            super(-1, -1);
      }
 
      public static DeletedEntry getUniqueDeletedEntry() {//Antika8ista ena HashEntry pou 8eloume na diagrayoume me enan DeletedEntry wste na mhn xalaei o pinakas twn antikeimenwn
            if (entry == null)
                  entry = new DeletedEntry();
            return entry;
      }
}