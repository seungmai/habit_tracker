// rank.js

import {firestore} from "../../firebase";

const rank_db = firestore.collection("rank");

const ADD_USER_NAME = "rank/ADD_USER_NAME";
const ADD_USER_MESSAGE = "rank/ADD_USER_MESSAGE";
const ADD_RANK = "rank/ADD_RANK";
const GET_RANK = "rank/GET_RANK";
const IS_LOADED = "rank/IS_LOADED";

const initialState = {
  user_name: "",
  user_message: "",
  user_score: "",
  score_text: {
    60: "말할 가치도 없는 새끼",
    80: "역시는 역시인가",
    100: "변태새끼ㅋ",
  },
  ranking: [],
  is_loaded: false,
};

// Action Creators
export const addUserName = (user_name) => {
  return { type: ADD_USER_NAME, user_name };
};

export const addUserMessage = (user_message) => {
  return { type: ADD_USER_MESSAGE, user_message };
};

export const addRank = (rank_info) => {
  return { type: ADD_RANK, rank_info };
};

export const getRank = (rank_list) => {
  return { type: GET_RANK, rank_list };
};

export const isLoaded = (loaded) => {
  return {type: IS_LOADED, loaded};
}

export const addRankFB = (rank_info) => {
  return function (dispatch) {
    dispatch(isLoaded(false));

    let rank_data = {
      message: rank_info.message,
      name: rank_info.name,
      score: rank_info.score,
    };
    rank_db.add(rank_data).then((doc) => {
      console.log(doc.id);
      rank_data = { ...rank_data, id: doc.id, current: true };
      dispatch(addRank(rank_data));
      
    });
  };
}


export const getRankFB = () => {
  return function (dispatch){

    dispatch(isLoaded(false));

    rank_db.get().then((docs) => {
      let rank_data = [];

      docs.forEach((doc) => {
        rank_data = [...rank_data, {id: doc.id, ...doc.data()}];
      });

      dispatch(getRank(rank_data));
      dispatch(isLoaded(true));

    })
  }
}



// Reducer
export default function reducer(state = initialState, action = {}) {
  switch (action.type) {
    case "rank/ADD_USER_NAME": {
      return { ...state, user_name: action.user_name };
    }

    case "rank/ADD_USER_MESSAGE": {
      return { ...state, user_message: action.user_message };
    }

    case "rank/ADD_RANK": {
      return { ...state, ranking: [...state.ranking, action.rank_info] };
    }

    case "rank/GET_RANK": {

      let ranking_data = [...state.ranking];

      const rank_ids = state.ranking.map((r, idx) => {
        return r.id;
      });
      console.log(rank_ids);

      const rank_data_fb = action.rank_list.filter((r, idx) => {
        if(rank_ids.indexOf(r.id) === -1){
          ranking_data = [...ranking_data, r];
        }
      });
      console.log(ranking_data);

      return { ...state, ranking: ranking_data };
    }

    case "rank/IS_LOADED": {
      return {...state, is_loaded: action.loaded};
    }

    default:
      return state;
  }
}