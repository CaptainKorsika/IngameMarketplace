import MenuScreen from "./MenuScreen/MenuScreen";
import StartingScreen from "./StartingScreen/StartingScreen";
import {FocusItemObject} from "../../Interfaces/FocusItemObject";


interface MenuProps {
    isCurrentlyPlaying: boolean
    focusItem: FocusItemObject
    handleNextDay: () => void
    handleItemTrade: (isBuying: boolean, amount: number) => void
    handleAmountChange: (amount: number) => void
    money: number
    amount: number
    totalPrice: string
    enoughMoney: boolean

}

const Menu = (props: MenuProps) => {
    return (
        <div className="menu-screen-container">
            {props.isCurrentlyPlaying ? (
                <MenuScreen focusItem={props.focusItem}
                            handleNextDay={props.handleNextDay}
                            handleItemTrade={props.handleItemTrade}
                            handleAmountChange={props.handleAmountChange}
                            money={props.money}
                            amount={props.amount}
                            totalPrice={props.totalPrice}
                            enoughMoney={props.enoughMoney}
                />
            ) : (
                <StartingScreen/>
            )}
        </div>
    );
}

export default Menu;
