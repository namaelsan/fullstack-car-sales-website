import { Injectable } from '@angular/core';
import { Car } from './car.models';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private readonly CART_KEY = "user_cart";
  private cartSubject = new BehaviorSubject<number[]>(this.loadCart());

    private loadCart() {
      const data = localStorage.getItem(this.CART_KEY);
      return data ? JSON.parse(data): [];
    }

    private saveCart(cart: number[]) {
      localStorage.setItem(this.CART_KEY, JSON.stringify(cart));
    }

    addToCart(id: number) {
      let updatedCart = this.cartSubject.getValue();
      let index = updatedCart.findIndex(i => i === id);
      if (index !== -1) {
        throw("Car already exists inside the cart")
      }

      updatedCart.push(id);
      this.cartSubject.next(updatedCart);
      this.saveCart(updatedCart);
    }

    removeFromCart(id: number) {
      let updatedCart = this.cartSubject.getValue();
      let index = updatedCart.findIndex(i => i === id);
      if (index == -1) {
        console.error("Car couldnt be found in cart")
      }
      updatedCart.splice(index,1);

      this.cartSubject.next(updatedCart);
      this.saveCart(updatedCart);
    }

    updateCart(id: number) {
      let updatedCart = this.cartSubject.getValue();
      let index = updatedCart.findIndex(i => i === id);
      if (index == -1) {
        console.error("Car couldnt be found in cart")
        return
      }
      updatedCart[index] = id;

      this.cartSubject.next(updatedCart);
      this.saveCart(updatedCart);
    }

    getCart() {
      return this.cartSubject.getValue();
    }

    getTotalPrice(): number {
      let total: number = 0;
      // let cart = this.cartSubject.getValue();
      // cart.forEach((c) => {
      //   total += c.price;
      // })
      return total
    }

    getTotalElements(): number {
      let cart = this.cartSubject.getValue();
      return cart.length
    }

    clearCart() {
      this.saveCart([]);
      this.cartSubject.next([]);
    }

  }
