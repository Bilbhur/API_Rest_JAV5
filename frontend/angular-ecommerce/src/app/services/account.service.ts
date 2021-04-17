import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { User } from '../common/user';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private loginUrl = 'http://localhost:8080/authenticate';
  private registerUrl = 'http://localhist:8080/register';

  private userSubject: BehaviorSubject<User>;
  public user: Observable<User>;

  constructor(private router: Router,
              private httpClient: HttpClient) {
    
    this.userSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('user')));
    this.user = this.userSubject.asObservable();
  }

  public get userValue(): User {

    return this.userSubject.value;
  }

  login(username, password) {

    return this.httpClient.post<User>(this.loginUrl, { username, password }).pipe(
      map(user => {
        // store user details and jwt token in local storage to keep user logged in between page refresh
        localStorage.setItem('user', JSON.stringify(user));
        this.userSubject.next(user);
        return user;
      })
    );
  }

  logout() {

    localStorage.removeItem('user');
    this.userSubject.next(null);
    this.router.navigate(['/products']);
  }

  register(user: User) {

      return this.httpClient.post(this.registerUrl, user);
  }

}
