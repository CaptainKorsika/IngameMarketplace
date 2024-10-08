import "./MenuScreen.css"
import {ItemObject} from "../../../Interfaces/ItemObject";
import {useState} from "react";
import NextDay from "./NextDay";

interface MenuScreenProps {
    focusItem: ItemObject,
    handleNextDay: () => void,
    handleItemTrade: (isBuying: boolean, amount: number) => void
}

const MenuScreen = (props: MenuScreenProps) => {
    const [amount, setAmount] = useState(1)

    const itemTrade = (isBuying: boolean, amount: number) => {
        props.handleItemTrade(isBuying, amount)
    }

    const handleAmountChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setAmount(Number(event.target.value))
    }


    return (
        <div className="menu">
            {props.focusItem != null ? (
                <div className="interaction-container">
                    <div className="merchant-data">
                        <div className="focus-item-object">
                            <h2>{props.focusItem.second}</h2>
                        </div>
                        <div className="focus-item-price">
                            <h2>Price: &nbsp; ${props.focusItem.first.currentPrice}</h2>
                        </div>
                    </div>
                    <div className="focus-item-wrapper">
                        <div className="focus-item">
                            <div className="focus-item-object">
                                <img src={props.focusItem.first.image} alt={props.focusItem.first.name}
                                     className="focus-item-image"/>
                            </div>
                            <h2 className="focus-item-name">{props.focusItem.first.name}</h2>
                        </div>
                        <div className="focus-item-button-wrapper">
                            <button onClick={() => itemTrade(false, amount)}>Sell Product</button>
                            <button onClick={() => itemTrade(true, amount)}>Buy Product</button>
                        </div>
                        <div className="amount-input">
                            <h4>Amount:</h4>
                            <input type="number" min="1" max={props.focusItem.second} defaultValue="1"
                                   onChange={handleAmountChange}/>
                        </div>
                    </div>
                    <div className="player-data">
                        <div className="focus-item-object">
                            <h2>{props.focusItem.second}</h2>
                        </div>
                        <div className="focus-item-price">
                            <h2>Avg. Buying Price: &nbsp; ${props.focusItem.first.currentPrice}</h2>
                        </div>
                        <NextDay handleNextDay={props.handleNextDay}/>
                    </div>
                </div>) : (
                <div className="interaction-container">
                    <div className="focus-item-wrapper">
                        <div className="focus-item-wrapper">
                            <div className="focus-item">
                                <div className="focus-item-object">
                                </div>
                                <h2 className="focus-item-name"></h2>
                                <div className="focus-item-amount">
                                    <h2></h2>
                                </div>
                            </div>
                            <div className="focus-item-information">
                            </div>
                        </div>
                    </div>
                </div>
            )}


        </div>
    );
}

export default MenuScreen;
