// App.js

import './App.css';
import React from "react";
import {Route, Switch} from "react-router-dom";

import Start from "./Start";
import Quiz from "./Quiz";
import Score from "./Score";
import Message from "./Message";
import Ranking from "./Ranking";

import { withRouter } from "react-router";
import { connect } from "react-redux";

const mapStateToProps = (state) => ({
  ...state,
});

const mapDispatchToProps = (dispatch) => ({
  load: () => {
    
  },
  
});

class App extends React.Component{
  constructor(props){
    super(props);

    this.state = {
      
    };
  }

  render () {
    return (
      <div className="App">
        <Switch>
          <Route path="/quiz" component={Quiz} />
          <Route path="/" exact component={Start} />
          <Route path="/score" component={Score} />
          <Route path="/message" component={Message} />
          <Route path="/ranking" component={Ranking} />
        </Switch>
      </div>
    );
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(withRouter(App));