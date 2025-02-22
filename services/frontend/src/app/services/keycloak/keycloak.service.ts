import { Injectable } from '@angular/core';
import Keycloak from "keycloak-js";
import { UserProfile } from "./user-profile";

@Injectable({
  providedIn: 'root'
})
export class KeycloakService {

   private _keycloak: Keycloak | undefined;
   private _profile: UserProfile | undefined;


  get keycloak() {
    if (!this._keycloak) {
      // this._keycloak = new Keycloak({
      //   url: 'http://localhost:9098',
      //   realm: 'dupa',
      //   clientId: 'dupa-client-id'
      // });
      // this._keycloak = new Keycloak({
      //   url: 'http://localhost:9098',
      //   realm: 'commerceapp',
      //   clientId: 'commerceapp-client-id'
      // });
      this._keycloak = new Keycloak({
        url: 'http://localhost:9098',
        realm: 'project-x',
        clientId: 'project-x-clientID'
      });
    }
    return this._keycloak;
  }
  get profile(): UserProfile | undefined {
    return this._profile;
  }


  async init() {
    console.log("Authentication the user...");
    const authenticated =  await this.keycloak.init({
      onLoad: 'login-required',
    });

    if (authenticated) {
      console.log("Authenticated user");
      this._profile = ( await this.keycloak.loadUserProfile()) as UserProfile;
      this._profile.token =  this.keycloak.token;
    }
  }

  login() {
    return this.keycloak.login();
  }

  logout() {
    return this.keycloak.logout({redirectUri: 'http://localhost:4200/'});
  }
}
