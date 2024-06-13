import axios from "axios";

const apiNoAuth = axios.create({
  baseURL: "http://localhost:8080",
  withCredentials: false,
  headers: {
    Accept: "application/json",
    "Content-Type": "application/json",
  },
});

const cars =  {
    getAll : () => apiNoAuth.get('cars'),
    getById: (id) => apiNoAuth.get(`cars/${id}`),
    getByBrand: (brand) => apiNoAuth.get(`cars/byBrand?brand=${brand}`),
    isCarAvailable: (id, startDate, endDate) => apiNoAuth.get(`cars/${id}/available`, { params: { startDate, endDate } })
}

const users = {
  authenticate: (username, password) => apiNoAuth.post('/users/authenticate', { username, password }),
  createUser: (userData) => apiNoAuth.post("/users/create", userData),
  updatePassword: (userData) => apiNoAuth.put("/users/updatePassword", userData),
}

const myRentals = {
  getMyRentals: (username, password) => {
    const apiAuth = axios.create({
      baseURL: "http://localhost:8080",
      withCredentials: true,
      headers: {
        Accept: "application/json",
        Authorization: 'Basic ' + btoa(username + ':' + password),
        "Content-Type": "application/json",
      },
    });
    return apiAuth.get('/rentsFilter');
  },
  createRent: (username, password, rentData) => {
    const apiAuth = axios.create({
      baseURL: "http://localhost:8080",
      withCredentials: true,
      headers: {
        Accept: "application/json",
        Authorization: 'Basic ' + btoa(username + ':' + password),
        "Content-Type": "application/json",
      },
    });
    return apiAuth.post('/rent/create', rentData);
  }

};

export default {
  cars,
  users,
  myRentals
}
