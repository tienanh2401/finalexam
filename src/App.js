import './App.css';
import { db } from './Service';
import { useState,useEffect } from 'react';
function App() {
    const [id,setId] = useState(0)
    const [title,setTitle] = useState("");
    const [author,setAuthor] = useState("");
    const [year,setYear] = useState("")
    const [books,setBooks] = useState([])
//
    useEffect(() => {
        db.books.toArray().then(items => setBooks(items))
    },[])  
    const clearForm = () => {
        setId(0)
        setTitle("")
        setAuthor("")
        setYear("")
    }
    const save = (id,title,author,year) => {
        if(id==0) {
            db.books.add({title: title, author: author, year: year})
        } else {
            db.books.update(id,{title: title, author: author, year: year})
        }
        db.books.toArray().then(items => setBooks(items))
        clearForm();
    }
    const loadDataEdit = (id) => {
        db.books.get(id).then(item => {
            setId(item.id)
            setTitle(item.title)
            setAuthor(item.author)
            setYear(item.year)
        })
    }
    const deleteItem = (id) => {
        db.books.delete(id);
        db.books.toArray().then(items => setBooks(items))
        
    }
    return(
        <header className="Push-form">
          <h3>Add a new books</h3>
        <input type="hidden" value={id}></input>
        <div>
            
            <label>Title</label>
            <input type="text" value={title} onChange={e => setTitle(e.target.value)}></input>
        </div>
        <div>
            <label>Author</label>
            <input type="text"value={author} onChange={e => setAuthor(e.target.value)}></input>
        </div>
        <div>
            <label>Year</label>
            <input type="number"value={year} onChange={e => setYear(e.target.value)}></input>
        </div>
        <button className='button-up' onClick={() => save(id,title,author,year)}>Add</button>
        <h3>A list of Books</h3>
        <table >
            <thead>  
                <th>Title</th>
                <th>Author</th>
                <th>Year</th>
                <th>Action</th>
            </thead>
            <tbody>
                {books.map(item => (
                    <tr>
                    <td>{item.title}</td>
                    <td>{item.author}</td>
                    <td>{item.year}</td>
                    <td>
                        <button className="button-down" onClick={() => {
                           
                            deleteItem(item.id)}}>Delete</button>
                        <button className="button-down" onClick={() => loadDataEdit(item.id)}>Edit</button>
                    </td>
                </tr>
                ))}
            </tbody>
        </table>

        </header>
    )
}

export default App;
