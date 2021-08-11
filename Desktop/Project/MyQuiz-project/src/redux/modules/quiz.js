// quiz.js

const GET_QUIZ = "quiz/GET_QUIZ";
const ADD_ANSWER = "quiz/ADD_ANSWER";
const RESET_ANSWER = "quiz/RESET_ANSWER";

const initialState = {
    name: "승민이",
    score_texts: {
    60: "말할 가치도 없는 새끼",
    80: "역시는 역시인가:)",
    100: "변태새끼",
    },
    answers: [],
     quiz: [
      { question: "승민이는 남을 대할 때 예의가 바르다.", answer: "O" },
      { question: "승민이는 여자친구가 있다.", answer: "X" },
      { question: "승민이는 인성 문제있다.", answer: "X" },
      { question: "승민이는 야하다.", answer: "O" },
      { question: "승민이는 매운걸 좋아하지만 매운걸 못먹는다.", answer: "O" },
      { question: "승민이는 투핫을 즐겨본다.", answer: "O" },
      { question: "승민이는 환승연애를 싫어한다.", answer: "X" },
      { question: "승민이는 코딩을 즐겁고 즐겁고 즐겁게 한다.", answer: "X" },
      { question: "승민이는 친 누나와 사이가 원만하다.", answer: "O" },
      { question: "지금 내 기분은 좋다(80점 못넘는 너는 인간도 아님!).", answer: "O" },
  ],
};

export const getQuiz = (quiz_list) => {
    return {type: GET_QUIZ, quiz_list};
};

export const addAnswer = (answer) => {
    return { type: ADD_ANSWER, answer };
};
  
  export const resetAnswer = () => {
    return { type: RESET_ANSWER };
}

export default function reducer(state = initialState, action = {}) {
    switch (action.type) {
      case "quiz/GET_QUIZ": {
        return { ...state, quiz: action.quiz_list };
      }
  
      case "quiz/ADD_ANSWER": {
        return { ...state, answers: [...state.answers, action.answer] };
      }
  
      case "quiz/RESET_ANSWER": {
        return {...state, answers: []};
      }
  
      default:
        return state;
    }
  }