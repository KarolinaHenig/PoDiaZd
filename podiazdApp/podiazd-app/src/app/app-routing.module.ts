import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CarRepairShopSelectionComponent } from './car-repair-shop-selection/car-repair-shop-selection.component';
import { CarSelectionComponent } from './car-selection/car-selection.component';
import { FindCarRepairShopComponent } from './find-car-repair-shop/find-car-repair-shop.component';
import { HomePageComponent } from './home-page/home-page.component';
import { LoginComponent } from './login/login.component';
import { MulfunctionSelectionComponent } from './mulfunction-selection/mulfunction-selection.component';
import { RateCarRepairShopComponent } from './rate-car-repair-shop/rate-car-repair-shop.component';
import { RegistrationComponent } from './registration/registration.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'registration', component: RegistrationComponent},
  { path: 'home-page', component: HomePageComponent},
  { path: 'car-repair-shop-selection', component: CarRepairShopSelectionComponent},
  { path: 'car-selection', component: CarSelectionComponent},
  { path: 'mulfunction-selection', component: MulfunctionSelectionComponent},
  { path: 'find-car-repair-shop', component: FindCarRepairShopComponent},
  { path: 'rate-car-repair-shop', component: RateCarRepairShopComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
