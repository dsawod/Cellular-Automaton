
title: readMe
author: Devendra Sawod
language: en-US

src/ project summary
  The first part of the project is to implement One Dimensional Cellular Automaton
  where the sequence of left neighbor, self and right neighbor determine the
  next state of the element. The state for each possible sequence is determined by
  the binary representation of the rule number.

  The second part is the implementation of Conway`s Game of Life. Each cell has eight neighbors
  and number of alive neighbors determine the next state of the cell.

  The last part is Langton`s Loop where there are only four neighbors. The slight twist is that the
  neighbors have rotational symmetry. For a given string 123456, 1 is the state of cell being examined
  and 2345 are its neighbors.  For example, 2 could be to the north, 3 to the east 4to the south and 5 to the west.
  Or it could be 2 to the east, 3 to the south,4 to the west and 5 to the north and so on.
  6 is the nest state of the cell if positions of its neighbors are correct.

  src/incompete features
  
  
  
