import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalycticsComponent } from './analyctics.component';

describe('AnalycticsComponent', () => {
  let component: AnalycticsComponent;
  let fixture: ComponentFixture<AnalycticsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SolicitudesComponent],
    }).compileComponents();

      declarations: [AnalycticsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AnalycticsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
