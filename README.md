# To-watch application

## What will the application do?

This project will keep track of movies that I have watched, am watching, and want to watch in the future. 

There are **3** distinct parts of this project: movies that I have finished watching, ones that I am watching, and ones that I want to watch (to-watch list).

General features:
- Classifying a movie as watched, currently watching, or to-watch
- Writing down notes/reviews
- Searching and filtering by genre
- Stats and insights (total watch time)
- Displaying lists of movies based on genres or whether the movie is in the to-watch, currently watching, or finished list
- Removing from each category 
- Changing movie details
- Adding movies

## Who will use it?

I, and maybe a few friends will be using this application

## Why is this project of interest to you?

I spend a lot of my time watching Movies. I also like to be organized, so it would be nice to have something that could combine these two aspects together. I like how Netflix does their "list", but I don't just watch movies on Netflix, so it would be helpful if I could keep track of movies on various different platforms

## User Stories

- As a user, I want to create movies with a status, name, and genre, and add it to the list of movies
- As a user, I want to view all movies on the list, and filter by genre, or their status (watched, currently watching, to-watch)
- As a user, I want to add notes, ratings, and watch time for the movies
- As a user, I want to change any details about the movie (status, name, genre, notes, ratings, watch time, episode, season)
- As a user, I want to categorize my movie as watched, currently watching, or to-watch
- As a user, I want the option to be able to save or not save my current movie list and be able to do so
- As a user, I want the option to load or not load the previous saved movie list and be able to do so

## Phase 4: Task 2

-Sat Mar 29 21:39:18 PDT 2025\
"movie" was added\
Sat Mar 29 21:39:21 PDT 2025\
" movie" was removed\
Sat Mar 29 21:39:38 PDT 2025\
"spiderman" was added\
Sat Mar 29 21:39:40 PDT 2025\
"name" was added\
Sat Mar 29 21:39:40 PDT 2025\
"batman" was added\
Sat Mar 29 21:39:43 PDT 2025\
Displayed information for name\
Sat Mar 29 21:39:45 PDT 2025\
Displayed information for batman\
Sat Mar 29 21:39:49 PDT 2025\
Filtered by status w\
Sat Mar 29 21:39:50 PDT 2025\
Filtered by status c\
Sat Mar 29 21:39:54 PDT 2025\
Filtered by genre genre\
Sat Mar 29 21:39:55 PDT 2025\
Filtered by genre action\

## Phase 4: Task 3

Currently, `MovieList` manages the addition and removal of `Movie` objects. This makes `MovieList` responsible for too many tasks. To improve this, I would refactor the design to introduce a `MovieManager` class that handles operations like adding, removing, and searching for movies. This would reduce the responsibilities of `MovieList`, and better follow the single responsibility rule.

Another change would be to make a class in the `panels` package that handles everything related to actions. Right now, when I try to add a new panel, I have to first create the panel, then add it to the menu bar, then check if it needs to update other panels, along with a few other things. I encountered many mistakes when doing this, and adding a new package that handles this process would greatly reduce the amount of null pointers, syntax errors, etc. 