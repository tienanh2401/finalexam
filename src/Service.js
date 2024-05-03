import Dexie from "dexie";
export const db = new Dexie('myDatabase');
db.version(1).stores({
  books: 'Title, Author, Year' // Primary key and indexed props
});