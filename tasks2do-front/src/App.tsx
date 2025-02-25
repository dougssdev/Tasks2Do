
import './App.css'
import '../src/styles/Global.css'
import Header from './pages/Cabecalho'
import AppRoutes from './routes/AppRoutes'
import { BrowserRouter } from 'react-router-dom'


function App() {

  return (

    <BrowserRouter>
    <div className='App'>
        <Header />
        <AppRoutes />
      </div>
    </BrowserRouter>
    
  )
}

export default App
