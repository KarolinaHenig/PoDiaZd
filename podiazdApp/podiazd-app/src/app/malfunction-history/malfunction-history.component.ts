import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { token } from '../auth-guard';

@Component({
  selector: 'app-malfunction-history',
  templateUrl: './malfunction-history.component.html',
  styleUrls: ['./malfunction-history.component.scss']
})
export class MalfunctionHistoryComponent implements OnInit {
  carRepairShops: Array<any> = [];

  displayedColumns: string[] = ['carRepairShopName', 'address', 'malfunction', 'car', 'myRate'];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.http.post('http://localhost:8080/api/v1/car-repair-shop/malfunction-history', {},
    {
      headers: {
        "Authorization": "Bearer " + token
      }
    }).subscribe((data: any) => {
      this.carRepairShops = data.carRepairShopRateList
    });

  }

}
