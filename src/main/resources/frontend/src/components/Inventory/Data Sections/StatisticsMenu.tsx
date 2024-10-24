import {Component, useEffect, useState} from "react";
import "./StatisticsMenu.css"
import SettingsScreenModal from "../../Menu/SettingsScreen/SettingsScreenModal";


interface StatisticsMenuProps {
    day: number
    showHighScoreModal: boolean
    handleShowHighScoreModal: (open: boolean) => void
    handleEndGame: () => void
}

const StatisticsMenu = (props: StatisticsMenuProps) => {
        return (
            <div className="statistics-menu">
                <div className="statistics-container">
                    <h2 className="statistics-header">Statistics</h2>
                    <div className="statistics-data">
                        <div className="statistic-items">
                            <h4 className="most-money">Most Money:</h4>
                            <h4 className="most-profit">Most profitable Day:</h4>
                            <h4 className="number-trades">Number of Trades:</h4>
                        </div>
                        <div className="statistic-data">
                            <h4>$1000</h4>
                            <h4>$103</h4>
                            <h4>5</h4>
                        </div>
                    </div>
                </div>
                <div className="meta-data">
                    <h2 className="days"> Day {props.day}/100</h2>
                    <SettingsScreenModal
                        showHighScoreModal={props.showHighScoreModal}
                        handleShowHighScoreModal={props.handleShowHighScoreModal}
                        handleEndGame={props.handleEndGame}
                    />
                </div>

            </div>
        );
}

export default StatisticsMenu;
