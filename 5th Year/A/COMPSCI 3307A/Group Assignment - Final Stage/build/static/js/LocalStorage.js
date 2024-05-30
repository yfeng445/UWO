export default class LocalStorage {
    constructor() {
      this.items = JSON.parse(localStorage.getItem('items')) || [];
    }
  
    create(data) {
      data.token = this.token;
  
      this.items.push(data);
  
      localStorage.setItem('items', JSON.stringify(this.items));
    }
  
    update(data) {
      let index = this.getIndexByToken(data.token);
  
      if (index !== -1) {
        this.items[index] = data;
  
        localStorage.setItem('items', JSON.stringify(this.items));
      }
    }
  
    delete(data) {
      let index = this.getIndexByToken(data.token);
  
      console.log(data.token);
      console.log(this.items);
  
      if (index !== -1) {
        this.items.splice(index, 1);
  
        localStorage.setItem('items', JSON.stringify(this.items));
      }
    }
  
    getIndexByToken(token) {
      for (let i = 0; i < this.items.length; i++) {
        if (this.items[i].token === token) {
          return i;
        }
      }
  
      return -1;
    }
  
    get token() {
      return Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15);
    }
  };