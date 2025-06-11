This is a well-structured program with good separation of concerns, proper encapsulation, and overall clean code. The variable naming in particular was pretty good.

**OVERALL: PASSED**

Minor tweaks:

* Lockers require a locker number to be valid. I'd force this via a constructor:
  ```java
  public Locker(int lockerNumber) {
      this.lockerNumber = lockerNumber;
  }
  ```

  This might break some other things in your code though, so no change required, just pointing it out.

* In LockerService you probably shouldn't have a `validatedLocker` field. This could bite you if you forget to clear or replace it. It would be better to return the validated locker to any calls that need it.

* `getValidation()` doesn't feel like the right name because it's not really a getter accessing a field. I'd name this `doValidation()` or simply `validate()`

* `displayMenu()` could probably be simplified if you broke it into submethods. What if it looked more like this:

  ```java
  public static void displayMenu(boolean lockerAvailable) {
  	if (lockerAvailable) {
          displayAvailableMenu();
      } else {
          displayUnavailbleMenu();
  	}
  }
  ```

  It's neat that you used replace the way you did, but this is a case where clever isn't as good as readable/simple.