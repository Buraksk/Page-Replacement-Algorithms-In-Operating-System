cmake_minimum_required(VERSION 3.9)
project(PageReplacementAlgorithm C)

set(CMAKE_C_STANDARD 99)

add_executable(PageReplacementAlgorithm main/main.c include/node.h include/linkedlist.h)