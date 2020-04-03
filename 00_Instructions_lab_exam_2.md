# COMP 1502 - Lab Exam 2

This is a take home lab exam. It is intended to take one hour to complete, but you may hand it in at any point in time before 3pm, Friday April 3. Lab exams submitted after this time will be assessed a grade of F.

You are free to use any resources you want, _with the exception of any other person, online or otherwise_.

The rubric for this quiz is provided at the bottom of this document.

## Overview

[Lindenmayer systems](https://en.wikipedia.org/wiki/L-system) (L-systems) are a way to model change over time by manipulating strings of symbols. L-systems can be very complicated but we will consider a very simplified model here.

L-systems are defined by a set of rules, which interact with a sequence of symbols. Over time, the rules of the system build interesting new sequences.

To start, each L-system must have a **starting sequence** and a set of **rules**.

Each rule has two parts, a **match** and a **body**. They are usually written Match->Body, to show that the match becomes the body.

When processed, every symbol in the sequence of symbols that corresponds to a **match** is replaced with the matching **body**. The result of this replacment becomes the next sequence.

#### IMPORTANT

If you have more than one rule, they are _all_ applied to the _current sequence_ - **don't** apply the first rule and then immediately apply the second rule to the result of the first rule! (The examples below, especially examples 3 and 4, should make this clearer.)

## Examples

It's easier to understand these systems through examples, so let's look at a few.

### Example 1

Say we have this rule:

> **{A->A}**

and this starting sequence:

> **A**

This will produce the following very exciting behaviour over time:

<pre>
Sequence 0: <b>A</b>
Sequence 1: <b>A</b>
Sequence 2: <b>A</b>
Sequence 3: <b>A</b>
Sequence 4: <b>A</b>
</pre>

### Example 2

Say we have these rules:

> **{A->B, B->A}**

and this starting sequence (which we'll call Sequence 0):

> **A**

This will produce the following:

<pre>
Sequence 0: <b>A</b>
Sequence 1: <b>B</b> 
Sequence 2: <b>A</b>
Sequence 3: <b>B</b>
Sequence 4: <b>A</b>
</pre>

### Example 3

Say we have these rules:

> **{A->AB, B->B}**

and this starting sequence:

> **A**

This will produce a system that grows over time:

<pre>
Sequence 0: <b>A</b>
Sequence 1: <b>AB</b>
Sequence 2: <b>ABB</b>
Sequence 3: <b>ABBB</b>
Sequence 4: <b>ABBBB</b>
</pre>

Here is a detailed play-by-play of what's going on here:

<pre>
  0:               A             starting sequence
                  / \
  1:             A   B           the single A in the starting sequence spawned into AB by rule (A → AB), rule (B → B) couldn't be applied
                /|     \
  2:           A B      B        former string AB with all rules applied: A spawned into AB again, B turned into B
             / | |      | 
  3:        A  B B      B        again, A turns into AB, while the B's from previous step stay as B's
           /|  | |      |
  4:      A B  B B      B  
</pre>

### Example 4

Say we have these rules:

> **{A->AB, B->C, C->}**
>
> _That last rule is NOT a typo; it replaces a C with nothing - that is, deletes it!_

and this starting sequence:

> **A**

This will produce a system shows this behaviour:

<pre>
Sequence 0: <b>A</b>
Sequence 1: <b>AB</b>
Sequence 2: <b>ABC</b>
Sequence 3: <b>ABC</b>
Sequence 4: <b>ABC</b>
</pre>

<pre>
  0:               A             starting sequence
                  / \
  1:             A   B           the single A in the starting sequence spawned into AB by rule (A → AB), other rules don't apply
                /|     \
  2:           A B      C        former string AB with all rules applied: A spawned into AB again, B turned into C
             / | |      | 
  3:        A  B C               A turns into AB, while the B from previous step becomes a C, and the previous C is nuked
           /|  | |      |
  4:      A B  C           
</pre>

## Requirements

You will write a simple system to model L-system rules as classes.

You have been provided with a `Main` class to show how the system should behave.

### `LLine`

You have also been provided with a `LLine` which provides the basic methods needed for the sequence to operate. Notice that it has a constructor which takes an array of chars and a set of rules. It also has two methods provided for you: a `toString` which makes it easy to see what the line is and a method `listToArray()` which takes a List or any subclass (like ArrayList) of Character and returns an array of `char[]`. These are provided for your convenience.

`LLine` also has a stub method `process()` which you will implement. In it, you will take the current sequence of characters (`line`) and replace it with a new sequence of characters, which you will get by applying rules.

### Rules

You will have to create rules. Each rule will be implemented in its own class, each of which is a subclass for the provided abstract class `LRule`. For each you will have to implement the two abstract methods `getMatch()` which returns the match portion of the rule and `getBody()` which returns the body portion of the rule. Each rule will be named Match_Body to help us identify classes.

#### For Example

The rule `A->AB` would be implement as the class `A_AB` which would have two methods:

- `getMatch()` which would return `A` and
- `getBody()` which would `[A, B]`

You will have to implement subclasses for 7 rules.

- `A->A`
- `A->AA`
- `A->BC`
- `B->A`
- `C->B`
- `A->Q`
- `A->` (a maps to an empty array of chars, name this class A_X)

### Exceptions

Additionally, you must create 2 custom exceptions, which are each thrown by the `.process()` method in `LLine`.

These are:

- `LSystemSymbolException`, which should be thrown if a symbol is in the sequence for which there is no rule (so if the letter Q is in the sequence, but no rule has Q->(something) this exception should be thrown)
- `LSystemLengthException`, which should be thrown any time the sequence of characters has no characters in it.

These must be checked exceptions. These should be (and already are) caught in the Main class

### Tests

Each of the 7 LRule subclasses should have a test class which tests if each class has been correctly implemented.

### Hints

- Consider starting by getting a basic version of `.process()` running so you can test it.
- Create one Rule class first for testing (A->A is a good candidate).
- Once you have that rule working, you can quickly copy it to produce the others.
- Once you have all of the rules completed you can finalize your `.process()` method
- Don't forget you can use the array initializer `char[] x = {'A', 'B'}` or `char x = {'A'}` to set up arrays of chars.

## Notes

- remember to save and commit often and keep an eye on your tests and warnings
- you can use the Main provided to see if your code is behaving reasonably, or you can just rely on the automated tests. Or both.
- you can choose to drag and drop these files into an eclipse project or to connect this to your eclipse as we have in tutorial. If you do this you must ensure that the files are in the correct folder (src); otherwise, they cannot be found and may not be marked.

---

## Rubric

### Grade: A range

- Compiles
- `.process()` and all rule classes written and behaving correctly.
- All exceptions written, and thrown appropriately by `.process()`
- All tests for the rule classes written.
- Follows all naming conventions (maybe with 1 or 2 minor slips)
- Code is easy to read and understand because it is formatted nicely, everything is named expressively, and constants are used when appropriate
- Tests have been added and correctly test the behaviour of the classes.

### Grade: B range

- This is like an A range, but is missing a significant component. Several rules or exceptions for example.
- A lab exam might also be graded as a B if the testing is insufficient, even if the solution would normally be graded as an A.
- An A quality solution with several slips in convention, or noticeably harder to read/understand because of formatting/naming issues

### Grade: C range

- Doesn't compile, but the instructor can tell that the methods present would work correctly, if not for some minor syntax issue; otherwise, everything else is at a B range
- You will also get a C range if the code compiles and behaves as expected, but frequent slips in convention/formatting/naming make the code confusing and hard to follow

### Grade: D

- Like a C range, but the instructor can see that numerous methods are incomplete or off-base

### Grade: F

- No attempt, or the code present is wildly incorrect

## Bonus

A bonus of one grade step (B to B+ for example) may be granted if you submit interesting pattern of rules in addition to the ones shown above.
