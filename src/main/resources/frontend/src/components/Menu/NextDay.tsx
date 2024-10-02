import "./NextDay.css"

interface NextDayProps {
    handleNextDay: () => void
}

const NextDay = (props: NextDayProps) => {
    return (
        <div className="menu-screen-container">
            <button className="next-day-button" onClick={props.handleNextDay}>Next Day</button>
        </div>
    );
}

export default NextDay;
