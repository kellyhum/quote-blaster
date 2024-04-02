# Personal Project
## A Java game about blasting words that form a quote

### What is it?
This is a game where the objective is to shoot words in a quote and acquire as many
points possible. Words will fall from the top of the page, and the player will be positioned at 
the middle bottom of the screen. Unable to move, the player will only be able to aim at the words, 
but will have a full 180 degree range of shooting motion.

To summarize, here are the following key objects will be on screen:
- player
- bullets
- words
- quote window
  - input for adding quotes
  - viewing the quote list
  - playing a specific quote

### Who's the target audience and why am I making this?
This game is targeted towards fans of Harry Potter, or those who have knowledge of the series. As a reader 
myself, one of my favourite books is Tomorrow and Tomorrow and Tomorrow, in which the main characters are 
video game makers. Specifically, one of the characters develops a game called "EmilyBlaster", where the 
objective is to shoot Emily Dickinson quotes in the same manner. Inspired by this, I wanted to see if I 
could make my own version of "EmilyBlaster".

### User Stories
- As a user, I want to be able to add my own quote to the quote list
- As a user, I want to be able to view a list of the quotes that I can play
- As a user, I want to pick which quote I want to play (but only be allowed to play one quote at a time)
- As a user, I want to shoot bullets when I press the space bar
- As a user, I want to see my score increase when I hit the correct words
- As a user, I want to be able to save my game and quote list states when S is pressed (if I choose)
- As a user, I want to be able to load my saved game and quote list states when L is pressed (if I choose)

# Instructions for Grader
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by
  - launch the program
  - click on the "change quote" button at the top
  - click the add button
  - enter any specified input (0 based indexing)
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by
  - launch the program
  - click the "change quote" button
  - click the remove button
  - enter any specified input (also 0 based indexing)
- You can locate my visual component by...
  - running the program
- You can save the state of my application by
  - clicking the s button on your keyboard
- You can reload the state of my application by
  - clicking the l button on your keyboard
### Phase 4: Task 2
"[Tue Apr 02 15:18:53 PDT 2024
added quote 'testing quote' to quote list, Tue Apr 02 15:18:57 PDT 2024
removed quote 'testing quote' from quote list]"