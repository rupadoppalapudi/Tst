import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Todo_timerComponent } from './todo_timer.component';

describe('TodosComponent', () => {
  let component: Todo_timerComponent;
  let fixture: ComponentFixture<Todo_timerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Todo_timerComponent ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Todo_timerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
