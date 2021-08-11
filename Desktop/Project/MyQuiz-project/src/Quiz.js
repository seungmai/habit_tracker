// Quiz.js

import React from 'react';
import styled from "styled-components";
import Score from "./Score";
import SwipeItem from './SwipeItem';
import { addAnswer } from './redux/modules/quiz';
import Progress from "./Progress";

import { useSelector, useDispatch } from "react-redux";

const Quiz = (props) => {
  const dispatch = useDispatch();
  const answers = useSelector((state) => state.quiz.answers);
  const quiz = useSelector((state) => state.quiz.quiz);
  
  const num = answers.length;

  const onSwipe = (direction) => {
    let _answer = direction === "left"? "O" : "X";

    if(_answer === quiz[num].answer){
      dispatch(addAnswer(true));
    }else{
      dispatch(addAnswer(false));
    }
  }

  if (num > quiz.length -1) {
    return <Score {...props}/>;
  }

  return (
    <QuizContainer>
      <Progress/>
      <p>
        <span>{num + 1}번 문제</span>
      </p>
      {quiz.map((l, idx) => {
        if (num === idx) {
          return <Question key={idx}>{l.question}</Question>;
        }
      })}

      <AnswerZone>
        <Answer>{"O "}</Answer>
        <Answer>{" X"}</Answer>
      </AnswerZone>

      {quiz.map((l, idx) => {
        if (idx === num) {
          return <SwipeItem key={idx} onSwipe={onSwipe}/>;
        }
      })}
    </QuizContainer>
  );
};

const QuizContainer = styled.div`
    text-align: center;
    & > p > span {
        padding: 4px 16px;
        background-color: #dadafc;
        border-radius: 20px;
    } 
`;

const Question = styled.h1`
    fontsize: 1.5em;
`;

const AnswerZone = styled.div`
    width: 100vw;
    height: 100vh;
    display: flex;
    position: absolute;
    top: 0;
    left: 0;
    z-index: 1;
`;

const Answer = styled.div`
    width: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 10em;
    font-weight: 500;
    color: black;
`;

const DragItem = styled.div`
    position: fixed;
    top: 0;
    left: 0;
    width: 100vw;
    height: 100vh;
    z-index: 10;
    display: flex;
    align-items: center;
    justify-content: center;
    & > div{
        background-color: #dadafc;
        border-radius: 100px;
    }
    & img{
        max-width: 100px;
    }
`;

export default Quiz;