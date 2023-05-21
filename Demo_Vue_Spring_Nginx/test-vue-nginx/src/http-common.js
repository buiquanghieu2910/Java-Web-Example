import axios from 'axios';
const HTTP = axios.create({
  baseURL: '/api',
  timeout: 5000 // request timeout
})
export default HTTP;