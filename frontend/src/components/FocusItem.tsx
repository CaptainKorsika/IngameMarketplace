// src/components/MyClassComponent.js
import React, { Component } from 'react';
import "./FocusItemStyle.css"


class FocusItem extends Component {
    render() {
        return (
            <div className="background">
                <h1>Hello, World!</h1>
                <p>This is a class component.</p>
            </div>
        );
    }
}

export default FocusItem;
