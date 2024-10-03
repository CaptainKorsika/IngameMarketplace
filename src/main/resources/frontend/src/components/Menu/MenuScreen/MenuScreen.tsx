import "./MenuScreen.css"
import {ItemObject} from "../../../Interfaces/ItemObject";

interface MenuScreenProps {
    focusItem: ItemObject
}

const MenuScreen = (props: MenuScreenProps) => {
    return (
        <div className="menu">
            <div className="price-history">
                <h2>Average Price Documented Price History</h2>

            </div>
            <div className="interaction-container">
                <div className="focus-item-wrapper">
                    <div className="focus-item">
                        <div className="focus-item-image-container">
                            {props.focusItem != null ? (
                            <img src={props.focusItem.image} alt={props.focusItem.name} className="focus-item-image"/>
                            ) : (
                            <img src="" alt="" className="focus-item-image"/>)}
                        </div>
                        <h2 className="focus-item-name">Item Name Placeholder</h2>
                        <div className="focus-item-amount">
                            <h2>x654</h2>
                        </div>

                    </div>
                    <div className="focus-item-information">
                        <div className="focus-item-price">
                            <h2>Price: 5$</h2>
                        </div>
                        <div className="focus-item-average">
                            <h2>Average Price: 4$</h2>
                        </div>

                    </div>
                </div>
                <div className="focus-item-player-interaction">
                    <div className="amount-input">
                        <h4>Amount:</h4>
                        <input type="number"/>
                    </div>

                    <div className="focus-item-button-wrapper">
                        <button>Sell Product</button>
                        <button>Buy Product</button>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default MenuScreen;
