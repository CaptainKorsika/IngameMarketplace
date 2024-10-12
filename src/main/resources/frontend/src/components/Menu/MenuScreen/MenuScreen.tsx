import "./MenuScreen.css"
import {useState} from "react";
import NextDay from "./NextDay";
import {FocusItemObject} from "../../../Interfaces/FocusItemObject";

interface MenuScreenProps {
    focusItem: FocusItemObject,
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
                            <h2>{props.focusItem.merchantAmount}</h2>
                        </div>
                        <div className="focus-item-price">
                            {props.focusItem.merchantAmount != 0 ? (
                            <h2>Price: &nbsp; ${props.focusItem.price}</h2>
                            ) : (<h2></h2>)}
                        </div>
                    </div>
                    <div className="focus-item-wrapper">
                        <div className="focus-item">
                            <div className="focus-item-object">
                                <img src={props.focusItem.image} alt={props.focusItem.name}
                                     className="focus-item-image"/>
                            </div>
                            <h2 className="focus-item-name">{props.focusItem.name}</h2>
                        </div>
                        <div className="focus-item-button-wrapper">
                            <button disabled={props.focusItem.playerAmount <= 0} onClick={() => itemTrade(false, amount)}>Sell Product</button>
                            <button disabled={props.focusItem.merchantAmount <= 0 || props.focusItem.playerAmount >= 99} onClick={() => itemTrade(true, amount)}>Buy Product</button>
                        </div>
                        <div className="amount-input">
                            <h4>Amount:</h4>
                            <input type="number" min="1" max="99" defaultValue="1"
                                   onChange={handleAmountChange}/>
                        </div>
                    </div>
                    <div className="player-data">
                        <div className="focus-item-object">
                            <h2>{props.focusItem.playerAmount}</h2>
                        </div>
                        <div className="focus-item-price">
                            {props.focusItem.playerAmount != 0 ? (
                                <h2>Avg. Buying Price: &nbsp; ${props.focusItem.avgBuyingPrice}</h2>
                            ) : (
                                <h2></h2>
                            )}
                        </div>
                        <NextDay handleNextDay={props.handleNextDay}/>
                    </div>
                </div>) : (
                <div className="interaction-container">
                    <div className="merchant-data">
                        <div className="focus-item-object">
                        </div>
                    </div>
                    <div className="focus-item-wrapper">
                        <div className="focus-item">
                            <div className="focus-item-object"></div>
                            <h2 className="focus-item-name"></h2>
                        </div>
                    </div>
                    <div className="player-data">
                        <div className="focus-item-object">
                        </div>
                        <div className="focus-item-price">
                                <h2></h2>
                        </div>
                        <NextDay handleNextDay={props.handleNextDay}/>
                    </div>
                </div>
            )}


        </div>
    );
}

export default MenuScreen;
